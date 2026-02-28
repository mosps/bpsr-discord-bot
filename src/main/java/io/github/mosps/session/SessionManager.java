package io.github.mosps.session;

import java.util.Map;
import java.util.concurrent.*;

public class SessionManager {
    private static final Map<String, SessionData> sessions = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();
    private static ScheduledFuture<?> cleanerTask;

    private static final long TIMEOUT = TimeUnit.DAYS.toMillis(1);

    public static void register (String sessionId, SessionData sessionData) {
        sessions.put(sessionId, sessionData);
    }

    public static SessionData getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public static void removeSession(String sessionId) {
        sessions.remove(sessionId);
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
