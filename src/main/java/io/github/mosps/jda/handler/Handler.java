package io.github.mosps.jda.handler;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public abstract class Handler {

    protected static long requireGuildId(GenericEvent event) {
        if (!(event instanceof GenericGuildEvent guildEvent)) {
            throw new IllegalStateException("This event is not a guild event");
        }
        return guildEvent.getGuild().getIdLong();
    }
}
