package io.github.mosps.party;

import java.util.Map;
import java.util.concurrent.*;

public class PartyManager {
    private static final Map<String, Party> sessions = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();
    private static ScheduledFuture<?> cleanerTask;

    private static final long TIMEOUT = TimeUnit.MINUTES.toMillis(5);

    public static void register (String sessionId, Party party) {
        sessions.put(sessionId, party);
    }

    public static Party getSession(String sessionId) {
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
                Party session = entry.getValue();

                return currentTime - session.getCreatedTime() > TIMEOUT;
            });
        }, 1, 1, TimeUnit.MINUTES);
    }
}
