package io.github.mosps.session;

import net.dv8tion.jda.api.entities.Member;

import java.util.HashSet;
import java.util.Set;

public class SessionData {
    private final long ownerId;
    private final Set<Long> members = new HashSet<>();

    private final long createdTime = System.currentTimeMillis();

    public SessionData(long ownerId) {
        this.ownerId = ownerId;
        this.addMembers(ownerId);
    }

    public Set<Long> getMembers() {
        return members;
    }

    public void addMembers(Member member) {
        members.add(member.getIdLong());
    }

    public void removeMembers(Member member) {
        members.remove(member.getIdLong());
    }

    public void addMembers(long memberId) {
        members.add(memberId);
    }

    public void removeMembers(long memberId) {
        members.remove(memberId);
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public long getOwnerId() {
        return ownerId;
    }
}


