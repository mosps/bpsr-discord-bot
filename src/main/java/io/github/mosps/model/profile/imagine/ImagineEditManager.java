package io.github.mosps.model.profile.imagine;

import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.ProfileManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ImagineEditManager {

    private static final Map<Long, ImagineEditSession> sessions = new ConcurrentHashMap<>();

    public static ImagineEditSession get(long userId) {
        return sessions.computeIfAbsent(userId, id -> {
            Profile profile = ProfileManager.getProfile(id);

            return new ImagineEditSession(id, profile.getImagines());
        });
    }

    public static void remove(long userId) {
        sessions.remove(userId);
    }
}
