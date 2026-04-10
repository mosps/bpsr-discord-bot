package io.github.mosps.model.profile.imagine;

import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.ProfileManager;

import java.util.Map;
import java.util.concurrent.*;

public class ImagineEditManager {

    private static final Map<Long, ImagineEditSession> sessions = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();
    private static ScheduledFuture<?> cleanerTask;

    private static final long TIMEOUT = TimeUnit.MINUTES.toMillis(10);

    public static ImagineEditSession get(long userId) {
        ImagineEditSession session = sessions.get(userId);

        session.updateTime();

        return session;
    }

    public static ImagineEditSession createSession(long userId) {
        Profile profile = ProfileManager.getProfile(userId);
        return new ImagineEditSession(userId, profile.getImagines());
    }

    public static void remove(long userId) {
        sessions.remove(userId);
    }

    public static void startCleaner() {
        if (cleanerTask != null && !cleanerTask.isCancelled()) return;

        cleanerTask = cleaner.scheduleAtFixedRate(() -> {
            long currentTime = System.currentTimeMillis();

            sessions.entrySet().removeIf(entry -> {
                ImagineEditSession session = entry.getValue();
                return currentTime - session.getLastTime() > TIMEOUT;
            });
        }, 5, 5, TimeUnit.MINUTES);
    }
}
