package io.github.mosps.handlers.actions.party;

import io.github.mosps.party.Party;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class PartyLeaveAction extends PartyActionBase {

    @Override
    public void handle(ButtonInteractionEvent event, String[] customId) {
        Party session = getSession(event, customId);
        if (session == null) return;

        long userId = event.getUser().getIdLong();

        session.removeMembers(userId);

        updateMessage(event, session);
    }
}
