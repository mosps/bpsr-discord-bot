package io.github.mosps.profile;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileEditManager {

    private static final Map<Long, ProfileEditSession> sessions = new ConcurrentHashMap<>();

    public static ProfileEditSession get(long userId) {
        return sessions.computeIfAbsent(userId, ProfileEditSession::new);
    }

    public static void remove(long userId) {
        sessions.remove(userId);
    }
}
