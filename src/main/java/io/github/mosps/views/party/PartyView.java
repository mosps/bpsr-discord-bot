package io.github.mosps.views.party;

import java.util.Set;

public class PartyView {
    public long ownerId;
    public String partyId;
    public Set<Long> members;
    public int maxMembers;
    public boolean closed;

    public String destination;
    public String time;
    public String note;
}
