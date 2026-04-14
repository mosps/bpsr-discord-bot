package io.github.mosps.ui.views.party;

import io.github.mosps.model.data.Role;
import io.github.mosps.ui.views.profile.MemberView;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PartyView {
    public long ownerId;
    public String ownerName;
    public long partyId;
    public List<MemberView> members;
    public Map<Role, Integer> role;
    public boolean closed;

    public String destination;
    public String time;
    public String note;
}
