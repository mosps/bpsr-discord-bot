package io.github.mosps.profile;

import io.github.mosps.data.Classes;
import io.github.mosps.data.Imagines;
import io.github.mosps.util.ProfileStorage;
import io.github.mosps.views.profile.ProfileView;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ProfileManager {

    private static final Map<Long, Profile> profiles = new ConcurrentHashMap<>();

    public static void loadAll() {
        profiles.putAll(ProfileStorage.loadAll());
    }

    public static Profile getProfile(long userId) {
        return profiles.get(userId);
    }

    public static Profile getOrCreateProfile(long userId, String name) {
        Profile profile = profiles.get(userId);

        if (profile != null) return profile;

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
        profiles.put(profile.getUserId(), profile);
        ProfileStorage.save(profile);
    }

    public static ProfileView createView(Profile profile) {
        ProfileView view = new ProfileView();

        Classes classes = profile.getMainClass();
        Set<Classes> subClasses = profile.getSubClasses();
        Map<Imagines, String> imagines = profile.getImagines();

        view.userId = profile.getUserId();
        view.name = profile.getName();
        view.mainClass = classes != null
                ? classes.getDisplay()
                : "未設定";
        view.subClasses = subClasses.isEmpty()
                ? "未設定"
                : subClasses.stream()
                .map(Classes::getDisplay)
                .collect(Collectors.joining("\n"));
        view.imagines = imagines.isEmpty()
                ? "未設定"
                : imagines.entrySet().stream()
                .map(entry -> entry.getKey().getDisplay() + entry.getValue())
                .collect(Collectors.joining(""));

        return view;
    }
}
