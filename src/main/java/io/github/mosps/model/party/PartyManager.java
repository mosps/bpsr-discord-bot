package io.github.mosps.model.party;

import io.github.mosps.model.profile.Profile;
import io.github.mosps.util.storage.PartyStorage;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class PartyManager {
    private static Map<Long, Map<Long, Party>> partiesByGuild = new ConcurrentHashMap<>();
    private static Set<Long> loadedGuilds = ConcurrentHashMap.newKeySet();

    private static Map<Long, AtomicLong> counters = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();
    private static ScheduledFuture<?> cleanerTask;

    private static final long TIMEOUT = TimeUnit.DAYS.toMillis(30);

    private static void register(long guildId, long partyId, Party party) {
        partiesByGuild.computeIfAbsent(guildId, k -> new ConcurrentHashMap<>())
                .put(partyId, party);
    }

    private static void ensureLoaded(long guildId) {
        if (loadedGuilds.contains(guildId)) return;

        synchronized (PartyManager.class) {
            if (loadedGuilds.contains(guildId)) return;

            Map<Long, Party> loaded = PartyStorage.loadGuild(guildId);
            partiesByGuild.put(guildId, new ConcurrentHashMap<>(loaded));

            initCounter(guildId);

            loadedGuilds.add(guildId);
        }
    }

    private static void initCounter(long guildId) {
        long max = partiesByGuild.getOrDefault(guildId, Map.of()).keySet().stream()
                .mapToLong(Long::longValue)
                .max().orElse(0);

        counters.put(guildId, new AtomicLong(max));
    }

    private static long generatePartyId(long guildId) {
        return counters.computeIfAbsent(guildId, k -> new AtomicLong(0)).incrementAndGet();
    }

    public static void saveParty(long guildId, Party party) {
        PartyStorage.save(guildId, party);
    }

    public static synchronized boolean tryJoin(long guildId, Party party, Profile profile, long userId) {
        synchronized (party) {
            if (!PartyRoleManager.canJoin(guildId, party.getPreset(), party, profile)) {
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

    public static void setSettings(Party party, String dest, String time, String note) {
        party.setDestination(dest);
        party.setTime(time);
        party.setNote(note);
    }

    public static void changePreset(Party party, PartyRolePreset preset) {
        party.setPreset(preset);
    }

    public static void toggle(Party party) {
        if (party.isClosed()) {
            party.open();
        } else {
            party.close();
        }
    }

    public static Party createParty(long guildId, long ownerId) {
        ensureLoaded(guildId);

        Party party = new Party(generatePartyId(guildId), ownerId);
        register(guildId, party.getPartyId(), party);

        return party;
    }

    public static Party getParty(long guildId, long partyId) {
        ensureLoaded(guildId);
        return partiesByGuild.getOrDefault(guildId, Map.of()).get(partyId);
    }

    public static void deleteParty(long guildId, Party party) {
        partiesByGuild.get(guildId).remove(party.getPartyId());
        PartyStorage.delete(guildId, party);
    }

    public static synchronized void startCleaner() {
        if (cleanerTask != null && !cleanerTask.isCancelled()) return;

        cleanerTask = cleaner.scheduleAtFixedRate(() -> {
            long currentTime = System.currentTimeMillis();

            partiesByGuild.values().forEach(partyMap ->
                    partyMap.entrySet().removeIf(entry -> {
                        Party party = entry.getValue();

                        return currentTime - party.getCreatedTime() > TIMEOUT;
                    })
            );
        }, 1, 1, TimeUnit.DAYS);
    }
}
