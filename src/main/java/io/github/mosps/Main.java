package io.github.mosps;

import io.github.mosps.session.SessionManager;

public class Main {
    public static void main(String[] args) {
        new BPSRInviteManager().botStart(args);
        SessionManager.startCleaner();
    }
}