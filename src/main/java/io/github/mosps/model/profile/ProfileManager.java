package io.github.mosps.model.profile;

import io.github.mosps.util.storage.GuildMemberStorage;
import io.github.mosps.util.storage.ProfileStorage;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileManager {

    private static Map<Long, Profile> profiles = new ConcurrentHashMap<>();
    private static Map<Long, Set<Long>> guildMembers = new ConcurrentHashMap<>();

    public static void loadAll() {
        profiles.putAll(ProfileStorage.loadAll());
        guildMembers.putAll(GuildMemberStorage.loadAll());
    }
    
    public static void registerGuildMember(long guildId, long userId) {
        Set<Long> members = guildMembers.computeIfAbsent(guildId, k -> new HashSet<>());

        if (members.add(userId)) {
            GuildMemberStorage.save(guildMembers);
        }
    }

    public static List<Profile> getGuildProfiles(long guildId) {
        return guildMembers.getOrDefault(guildId, Set.of()).stream()
                .map(profiles::get)
                .toList();
    }

    public static Profile getProfile(long userId) {
        return profiles.get(userId);
    }

    public static Profile getOrCreateProfile(long guildId, long userId) {
        Profile profile = profiles.get(userId);

        registerGuildMember(guildId, userId);

        if (profile == null) {
            profile = new Profile(userId);
            profiles.put(userId, profile);
        }

        return profile;
    }

    public static void saveProfile(Profile profile) {
        ProfileStorage.save(profile);
    }

    public static void stillOwnedImagines(Profile profile) {
        profile.getEquippedImagines().keySet().removeIf(key -> !profile.getImagines().containsKey(key));
    }
}
