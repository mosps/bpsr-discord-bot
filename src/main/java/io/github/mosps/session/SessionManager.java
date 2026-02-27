package io.github.mosps.session;

import java.util.Map;
import java.util.concurrent.*;

public class SessionManager {
    private static final Map<Long, SessionData> sessions = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();
    private static ScheduledFuture<?> cleanerTask;

    private static final long TIMEOUT = TimeUnit.DAYS.toMillis(1);

    public static void register (long messageId, SessionData sessionData) {
        sessions.put(messageId, sessionData);
    }

    public static SessionData getSession(long messageId) {
        return sessions.get(messageId);
    }

    public static void removeSession(long messageId) {
    }

    public static synchronized void startCleaner() {
        if (cleanerTask != null && !cleanerTask.isCancelled()) return;

        cleanerTask = cleaner.scheduleAtFixedRate(() -> {
            long currentTime = System.currentTimeMillis();

            sessions.entrySet().removeIf(entry -> {
                SessionData session = entry.getValue();

                return currentTime - session.getCreatedTime() > TIMEOUT;
            });
        }, 1, 1, TimeUnit.MINUTES);
    }
}
