package io.github.mosps.handlers.actions.party;

import io.github.mosps.session.SessionData;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import javax.swing.*;

public class PartyJoinAction extends PartyActionBase {

    @Override
    public void handle(ButtonInteractionEvent event, String[] customId) {
        SessionData session = getSession(event, customId);
        if (session == null) return;

        long userId = event.getUser().getIdLong();

        session.addMembers(userId);

        updateMessage(event, session);
    }
}
