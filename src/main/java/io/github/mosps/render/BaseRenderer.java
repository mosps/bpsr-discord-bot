package io.github.mosps.render;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.awt.*;
import java.util.List;

public abstract class BaseRenderer<T> implements Renderer<T> {

    protected EmbedBuilder baseEmbed() {
        return new EmbedBuilder();
    }

    protected RenderResult build(MessageEditData data, ActionRow row) {
        return new RenderResult(data, List.of(row));
    }

    protected RenderResult build(MessageEditData data, List<ActionRow> rows) {
        return new RenderResult(data, rows);
    }
}
