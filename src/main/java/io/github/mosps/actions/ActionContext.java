package io.github.mosps.actions;

import io.github.mosps.actions.data.ActionData;
import io.github.mosps.actions.data.EmptyData;
import io.github.mosps.util.customid.CustomId;

public class ActionContext {
    private final String messageId;
    private final long userId;
    private final String name;
    private final CustomId customId;
    private final ActionData data;

    public ActionContext(String messageId, long userId, String name, CustomId customId, ActionData data) {
        this.messageId = messageId;
        this.userId = userId;
        this.name = name;
        this.customId = customId;
        this.data = data;
    }

    public String getMessageId() {
        return messageId;
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

    public boolean isEmptyData() {
        return data instanceof EmptyData;
    }
}
