package io.github.mosps.render;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class BaseRenderer<T> implements Renderer<T> {

    protected EmbedBuilder baseEmbed() {
        return new EmbedBuilder()
                .setColor(Color.CYAN);
    }

    protected RenderResult build(EmbedBuilder embed, ActionRow rows) {
        return new RenderResult(embed.build(), List.of(rows));
    }
}
