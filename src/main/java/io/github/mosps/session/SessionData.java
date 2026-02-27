package io.github.mosps.session;

import net.dv8tion.jda.api.entities.Member;

import java.util.HashSet;
import java.util.Set;

public class SessionData {
    private final Set<Long> members = new HashSet<>();
    private final long createdTime = System.currentTimeMillis();

    public Set<Long> getMembers() {
        return members;
    }

    public void addMembers(Member member) {
        members.add(member.getIdLong());
    }

    public void removeMembers(Member member) {
        members.remove(member.getIdLong());
    }

    public long getCreatedTime() {
        return createdTime;
    }
}


