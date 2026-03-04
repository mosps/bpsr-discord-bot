package io.github.mosps.handlers.listener;

import io.github.mosps.handlers.handler.CommandHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.isAcknowledged()) {
            event.deferReply().queue();
        }
        CommandHandler.handle(event);
    }
}
