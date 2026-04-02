package io.github.mosps.views.party;

import io.github.mosps.data.Role;
import io.github.mosps.party.PartyRolePreset;

import java.util.Map;
import java.util.Set;

public class PartyView {
    public long ownerId;
    public String partyId;
    public Set<Long> members;
    public Map<Role, Integer> role;
    public boolean closed;

    public String destination;
    public String time;
    public String note;
}
