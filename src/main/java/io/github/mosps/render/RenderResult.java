package io.github.mosps.render;

import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.List;

public class RenderResult {
    private final MessageEmbed embed;
    private final List<ActionRow> components;

    public RenderResult(MessageEmbed embed, List<ActionRow> components) {
        this.embed = embed;
        this.components = components;
    }

    public MessageEmbed getEmbed() {
        return embed;
    }

    public List<ActionRow> getComponents() {
        return components;
    }
}
