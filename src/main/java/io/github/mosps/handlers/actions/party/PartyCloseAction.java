package io.github.mosps.handlers.actions.party;

import io.github.mosps.party.Party;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class PartyCloseAction extends PartyActionBase {

    @Override
    public void handle(ButtonInteractionEvent event, String[] customId) {
        Party session = getSession(event, customId);
        if (session == null) return;

        long userId = event.getUser().getIdLong();

        if (userId != session.getOwnerId()) {
            event.reply("募集作成者ではありません").setEphemeral(true).queue();
            return;
        }
        session.close();

        updateMessage(event, session);
    }
}
