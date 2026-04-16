package io.github.mosps.model.party.setting;

import io.github.mosps.model.profile.ProfileManager;

import java.util.HashMap;
import java.util.Map;

public class PartyMemberAddManager {
    private static Map<Long, PartyMemberAddSession> sessions = new HashMap<>();

    public static PartyMemberAddSession get(long userId, long guildId) {
        return sessions.computeIfAbsent(userId, id ->
                new PartyMemberAddSession(ProfileManager.getGuildProfiles(guildId))
        );
    }

    public static void remove(long userId) {
        sessions.remove(userId);
    }
}
