package io.github.mosps.profile.imagine;

import io.github.mosps.data.Imagines;
import io.github.mosps.profile.Profile;
import io.github.mosps.profile.ProfileManager;
import io.github.mosps.views.profile.imagine.ImagineEditView;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
