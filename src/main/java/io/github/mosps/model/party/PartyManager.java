package io.github.mosps.model.party;

import io.github.mosps.model.profile.Profile;
import io.github.mosps.util.PartyStorage;

import java.util.Map;
import java.util.concurrent.*;

public class PartyManager {
    private static final Map<String, Party> parties = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();
    private static ScheduledFuture<?> cleanerTask;

    private static final long TIMEOUT = TimeUnit.DAYS.toDays(7);

    public static void register(String partyId, Party party) {
        PartyManager.parties.put(partyId, party);
    }

    public static void loadAll() {
        parties.putAll(PartyStorage.loadAll());
    }

    public static void initCounter() {
        int max = parties.keySet().stream()
                .mapToInt(Integer::parseInt)
                .max().orElse(0);

        Party.setCounter(max);
    }

    public static void saveParty(Party party) {
        PartyStorage.save(party);
    }

    public static synchronized boolean tryJoin(Party party, Profile profile, long userId) {
        synchronized (party) {
            if (!PartyRoleManager.canJoin(party.getPreset(), party, profile)) {
                return false;
            }

            party.addMembers(userId);
            return true;
        }
    }

    public static synchronized void leave(Party party, long userId) {
        synchronized (party) {
            party.removeMembers(userId);
        }
    }

    public static void toggle(Party party) {
        if (party.isClosed()) {
            party.open();
        } else {
            party.close();
        }
    }

    public static Party createParty(long ownerId) {
        Party party = new Party(ownerId);
        register(party.getPartyId(), party);

        return party;
    }

    public static Party getParty(String partyId) {
        return parties.get(partyId);
    }

    public static void deleteParty(Party party) {
        parties.remove(party.getPartyId());
        PartyStorage.delete(party);
    }

    public static synchronized void startCleaner() {
        if (cleanerTask != null && !cleanerTask.isCancelled()) return;

        cleanerTask = cleaner.scheduleAtFixedRate(() -> {
            long currentTime = System.currentTimeMillis();

            parties.entrySet().removeIf(entry -> {
                Party party = entry.getValue();

                return currentTime - party.getCreatedTime() > TIMEOUT;
            });
        }, 1, 1, TimeUnit.DAYS);
    }
}
