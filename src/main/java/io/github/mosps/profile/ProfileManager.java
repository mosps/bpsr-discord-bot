package io.github.mosps.profile;

import io.github.mosps.util.ProfileStorage;
import io.github.mosps.views.profile.ProfileView;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileManager {

    private static final Map<Long, Profile> profiles = new ConcurrentHashMap<>();

    public static void loadAll() {
        profiles.putAll(ProfileStorage.loadAll());
    }

    public static Profile getOrCreateProfile(long userId, String name) {
        Profile profile = profiles.get(userId);

        if (profile != null) return profile;

        profile = ProfileStorage.load(userId);

        if (profile != null) {
            profiles.put(userId, profile);
            return profile;
        }

        profile = createProfile(userId, name);
        saveProfile(profile);

        return profile;
    }

    public static Profile createProfile(long userId, String name) {
        Profile profile = new Profile(userId, name);
        profiles.put(userId, profile);

        return profile;
    }

    public static void saveProfile(Profile profile) {
        ProfileStorage.save(profile);
    }

    public static ProfileView createView(Profile profile) {
        ProfileView view = new ProfileView();

        view.userId = profile.getUserId();
        view.name = profile.getName();
        view.mainClass = profile.getMainClass();
        view.subClasses = profile.getsubClasses();
        view.imagines = profile.getImagines();

        return view;
    }
}
