package io.github.mosps.model.party.setting;

import io.github.mosps.model.profile.Profile;

import java.util.List;

public class PartyMemberAddSession {
    private List<Profile> members;
    private int page = 0;

    public PartyMemberAddSession(List<Profile> members) {
        this.members = members;
    }

    public List<Profile> getMembers() {
        return members;
    }

    public int getPage() {
        return page;
    }

    public void nextPage() {
        page++;
    }

    public void prevPage() {
        page--;
    }
}
