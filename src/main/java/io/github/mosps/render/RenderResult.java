package io.github.mosps.render;

import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.util.List;

public class RenderResult {
    private final MessageEditData data;
    private final List<ActionRow> components;

    public RenderResult(MessageEditData data, List<ActionRow> components) {
        this.data = data;
        this.components = components;
    }

    public MessageEditData getMessageEditData() {
        return data;
    }

    public List<ActionRow> getComponents() {
        return components;
    }
}
