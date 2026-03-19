package io.github.mosps.handlers.actions;

import java.util.List;

public class ActionContext {
    private final long userId;
    private final String partyId;
    private final String name;
    private final String type;
    private final List<String> value;

    /*ButtonHandler*/
    public ActionContext(long userId, String name, String partyId) {
        this.userId = userId;
        this.partyId = partyId;
        this.name = name;
        this.type = null;
        this.value = null;
    }

    /*SelectMenuHandler*/
    public ActionContext(long userId, String name, String type, List<String> value) {
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.value = value;
        this.partyId = null;
    }

    /*CommandHandler*/
    public ActionContext(long userId, String name) {
        this.userId = userId;
        this.name = name;
        this.partyId = null;
        this.type = null;
        this.value = null;
    }

    public long getUserId() {
        return userId;
    }

    public String getPartyId() {
        return partyId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<String> getValue() {
        return value;
    }
}
