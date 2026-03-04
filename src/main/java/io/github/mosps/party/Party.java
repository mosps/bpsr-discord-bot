package io.github.mosps.party;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Party {
    private final long ownerId;
    private final String partyId = UUID.randomUUID().toString();
    private final Set<Long> members = new HashSet<>();

    private final long createdTime = System.currentTimeMillis();

    private int maxMembers = 5;
    private boolean closed = false;

    public Party(long ownerId) {
        this.ownerId = ownerId;
        this.addMembers(ownerId);
    }

    public Set<Long> getMembers() {
        return members;
    }

    public void addMembers(long memberId) {
        members.add(memberId);
    }

    public void removeMembers(long memberId) {
        members.remove(memberId);
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getPartyId() {
        return partyId;
    }

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        closed = true;
    }
}


