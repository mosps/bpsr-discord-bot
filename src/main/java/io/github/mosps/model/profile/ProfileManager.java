package io.github.mosps.model.profile;

import io.github.mosps.util.ProfileStorage;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileManager {

    private static Map<Long, Map<Long, Profile>> profilesByGuild = new ConcurrentHashMap<>();
    private static Set<Long> loadedGuilds = ConcurrentHashMap.newKeySet();

    private static void ensureLoaded(long guildId) {
        if (loadedGuilds.contains(guildId)) return;

        synchronized (ProfileManager.class) {
            if (loadedGuilds.contains(guildId)) return;

            Map<Long, Profile> loaded = ProfileStorage.loadGuild(guildId);
            profilesByGuild.put(guildId, new ConcurrentHashMap<>(loaded));

            loadedGuilds.add(guildId);
        }
    }

    public static Profile getProfile(long guildId, long userId) {
        ensureLoaded(guildId);
        return profilesByGuild.getOrDefault(guildId, Map.of()).get(userId);
    }

    public static Profile getOrCreateProfile(long guildId, long userId) {
        ensureLoaded(userId);

        return profilesByGuild
                .computeIfAbsent(guildId, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(userId, id -> {
                    Profile profile = new Profile(userId);
                    saveProfile(guildId, profile);
                    return profile;
                });
    }

    public static Profile createProfile(long guildId, long userId) {
        Profile profile = new Profile(userId);
        profilesByGuild.computeIfAbsent(guildId, k -> new ConcurrentHashMap<>())
                .put(userId, profile);

        return profile;
    }

    public static void saveProfile(long guildId, Profile profile) {
        ProfileStorage.save(guildId, profile);
    }

    public static void stillOwnedImagines(Profile profile) {
        profile.getEquippedImagines().keySet().removeIf(key -> !profile.getImagines().containsKey(key));
    }
}
