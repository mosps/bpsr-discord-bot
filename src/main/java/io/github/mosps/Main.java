package io.github.mosps;

import io.github.mosps.party.PartyManager;

public class Main {
    public static void main(String[] args) {
        new BPSRInviteManager().botStart(args);
        PartyManager.startCleaner();
    }
}