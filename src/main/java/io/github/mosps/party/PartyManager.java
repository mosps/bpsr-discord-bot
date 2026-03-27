package io.github.mosps.party;

import io.github.mosps.views.party.PartyView;

import java.util.Map;
import java.util.concurrent.*;

public class PartyManager {
    private static final Map<String, Party> parties = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();
    private static ScheduledFuture<?> cleanerTask;

    private static final long TIMEOUT = TimeUnit.MINUTES.toMillis(5);

    public static void register(String partyId, Party party) {
        PartyManager.parties.put(partyId, party);
    }

    public static void join(Party party, long userId) {
        party.addMembers(userId);
    }

    public static void leave(Party party, long userId) {
        party.removeMembers(userId);
    }

    public static void close(Party party) {
        party.close();
    }

    public static Party createParty(long ownerId) {
        Party party = new Party(ownerId);

        register(party.getPartyId(), party);

        return party;
    }

    public static Party getParty(String partyId) {
        return parties.get(partyId);
    }

    public static void removeParty(String partyId) {
        parties.remove(partyId);
    }

    public static PartyView createView(Party party) {
        PartyView view = new PartyView();

        view.destination = party.getDestination() != null
                ? party.getDestination()
                : "-";
        view.time = party.getTime() != null
                ? party.getTime()
                : "-";
        view.note = party.getNote() != null
                ? party.getNote()
                : "-";

        view.partyId = party.getPartyId();
        view.ownerId = party.getOwnerId();
        view.members = party.getMembers();
        view.maxMembers = party.getMaxMembers();
        view.closed = party.isClosed();

        return view;
    }

    public static synchronized void startCleaner() {
        if (cleanerTask != null && !cleanerTask.isCancelled()) return;

        cleanerTask = cleaner.scheduleAtFixedRate(() -> {
            long currentTime = System.currentTimeMillis();

            parties.entrySet().removeIf(entry -> {
                Party party = entry.getValue();

                return currentTime - party.getCreatedTime() > TIMEOUT;
            });
        }, 1, 1, TimeUnit.MINUTES);
    }
}
