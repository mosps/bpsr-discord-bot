package io.github.mosps.handlers.actions;

public class ActionContext {
    private final long userId;
    private final String partyId;

    public ActionContext(long userId, String partyId) {
        this.userId = userId;
        this.partyId = partyId;
    }

    public ActionContext(long userId) {
        this.userId = userId;
        this.partyId = null;
    }

    public long getUserId() {
        return userId;
    }

    public String getPartyId() {
        return partyId;
    }
}
