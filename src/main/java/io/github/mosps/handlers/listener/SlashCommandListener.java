package io.github.mosps.handlers.listener;

import io.github.mosps.handlers.actions.command.party.PartyCreateAction;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.isAcknowledged()) {
            event.deferReply().queue();
        }

    }
}
