package io.github.mosps.jda.handler;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;

import java.util.Objects;

public abstract class Handler {

    protected static long requireGuildId(GenericInteractionCreateEvent event) {
        if (!(event.isFromGuild())) {
            throw new IllegalStateException("This event is not a guild event");
        }
        return Objects.requireNonNull(event.getGuild()).getIdLong();
    }
}
