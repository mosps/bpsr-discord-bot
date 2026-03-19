package io.github.mosps.profile.imagine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ImagineEditManager {

    private static final Map<Long, ImagineEditSession> sessions = new ConcurrentHashMap<>();

    public static ImagineEditSession get(long userId) {
        return sessions.computeIfAbsent(userId, ImagineEditSession::new);
    }

    public static void remove(long userId) {
        sessions.remove(userId);
    }
}
