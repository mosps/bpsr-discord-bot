package io.github.mosps.model.profile;

import io.github.mosps.util.ProfileStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileManager {

    private static final Map<Long, Profile> profiles = new ConcurrentHashMap<>();

    public static void loadAll() {
        profiles.putAll(ProfileStorage.loadAll());
    }

    public static Profile getProfile(long userId) {
        return profiles.get(userId);
    }

    public static Profile getOrCreateProfile(long userId) {
        Profile profile = profiles.get(userId);

        if (profile != null) return profile;

        profile = createProfile(userId);
        saveProfile(profile);

        return profile;
    }

    public static Profile createProfile(long userId) {
        Profile profile = new Profile(userId);
        profiles.put(userId, profile);

        return profile;
    }

    public static void saveProfile(Profile profile) {
        profiles.put(profile.getUserId(), profile);
        ProfileStorage.save(profile);
    }

    public static void stillOwnedImagines(Profile profile) {
        profile.getEquippedImagines().keySet().removeIf(key -> !profile.getImagines().containsKey(key));
    }
}
