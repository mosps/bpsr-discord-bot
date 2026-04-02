package io.github.mosps.party;

import io.github.mosps.data.Role;

import java.util.Map;

public enum PartyRolePreset {

    NORMAL_5(Map.of(
            Role.TANK, 1,
            Role.HEALER, 1,
            Role.DPS, 3
    )),

    FREE_5(Map.of(Role.ALL, 5)),

    NORMAL_20(Map.of(
            Role.TANK, 2,
            Role.HEALER, 4,
            Role.DPS, 14
    )),

    FREE_20(Map.of(Role.ALL, 20));

    private final Map<Role, Integer> value;

    PartyRolePreset(Map<Role, Integer> value) {
        this.value = value;
    }

    public Map<Role, Integer> getValue() {
        return value;
    }
}
