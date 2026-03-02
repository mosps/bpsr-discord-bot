package io.github.mosps.views.party;

import java.util.Set;

public class PartyView {
    public long ownerId;
    public long sessionId;
    public Set<Long> members;
    public int maxMembers;
    public boolean closed;

}
