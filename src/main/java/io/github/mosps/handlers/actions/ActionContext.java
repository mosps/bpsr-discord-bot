package io.github.mosps.handlers.actions;

import io.github.mosps.handlers.actions.data.ActionData;
import io.github.mosps.util.customid.CustomId;

import java.util.List;

public class ActionContext {
    private final long userId;
    private final String name;
    private final CustomId customId;
    private final ActionData data;

    public ActionContext(long userId, String name, CustomId customId, ActionData data) {
        this.userId = userId;
        this.name = name;
        this.customId = customId;
        this.data = data;
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

    public <T extends ActionData> T getData(Class<T> type) {
        return type.cast(data);
    }
}
