package io.github.mosps.handlers.actions;

import io.github.mosps.util.customid.CustomId;

import java.util.List;

public class ActionContext {
    private final long userId;
    private final String name;
    private final CustomId customId;
    private final List<String> value;

    public ActionContext(long userId, String name, CustomId customId, List<String> value) {
        this.userId = userId;
        this.name = name;
        this.customId = customId;
        this.value = value;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public CustomId getCustomId() {
        return customId;
    }

    public List<String> getValue() {
        return value;
    }
}
