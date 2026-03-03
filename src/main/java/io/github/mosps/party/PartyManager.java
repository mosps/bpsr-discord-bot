package io.github.mosps.party;

import io.github.mosps.views.party.PartyView;

import java.util.Map;
import java.util.concurrent.*;

public class PartyManager {
    private static final Map<String, Party> parties = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();
    private static ScheduledFuture<?> cleanerTask;

    private static final long TIMEOUT = TimeUnit.MINUTES.toMillis(5);

    public static void register (String partyId, Party party) {
        PartyManager.parties.put(partyId, party);
    }

    public static Party getParty(String partyId) {
        return parties.get(partyId);
    }

    public static void removeParty(String partyId) {
        parties.remove(partyId);
    }

    public static PartyView createView(Party party) {
        PartyView view = new PartyView();

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
                Party session = entry.getValue();

                return currentTime - session.getCreatedTime() > TIMEOUT;
            });
        }, 1, 1, TimeUnit.MINUTES);
    }
}
