package io.github.mosps.ui.views.party;

import io.github.mosps.model.data.Role;

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
