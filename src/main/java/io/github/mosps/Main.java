package io.github.mosps;

import io.github.mosps.model.party.PartyManager;

public class Main {
    public static void main(String[] args) {
        new BPSRInviteManager().botStart(args);
        PartyManager.startCleaner();
    }
}