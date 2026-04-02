package io.github.mosps.party;

import io.github.mosps.data.Role;
import io.github.mosps.profile.Profile;
import io.github.mosps.profile.ProfileManager;

import java.util.Map;

public class PartyRoleManager {

    public static boolean canJoin(PartyRolePreset preset, Party party, Profile profile) {
        Role role = profile.getMainClass().getRole();
        Map<Role, Integer> presetMap = preset.getValue();

        if (presetMap.containsKey(Role.ALL)) {
            int limit = presetMap.get(Role.ALL);
            if (party.getMembers().size() >= limit) {
                return false;
            }
        }

        if (presetMap.containsKey(role)) {
            int limit = presetMap.get(role);

            long count = party.getMembers().stream()
                    .map(ProfileManager::getProfile)
                    .map(Profile::getMainClass)
                    .filter(c -> c.getRole() == role)
                    .count();

            if (count >= limit) {
                return false;
            }
        }

        return true;
    }
}
