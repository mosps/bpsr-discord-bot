package io.github.mosps.actions;

import io.github.mosps.actions.data.ActionData;
import io.github.mosps.actions.data.EmptyData;
import io.github.mosps.util.customid.CustomId;

public class ActionContext {
    private final long guildId;
    private final String messageId;
    private final long userId;
    private final String name;
    private final CustomId customId;
    private final ActionData data;

    public ActionContext(long guildId, String messageId, long userId, String name, CustomId customId, ActionData data) {
        this.guildId = guildId;
        this.messageId = messageId;
        this.userId = userId;
        this.name = name;
        this.customId = customId;
        this.data = data;
    }

    public long getGuildId() {
        return guildId;
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
