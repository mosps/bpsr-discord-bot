package io.github.mosps.handlers.actions.button.party;

import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import io.github.mosps.views.party.PartyView;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class PartyLeaveAction extends PartyActionBase {

    @Override
    public void handle(ButtonInteractionEvent event, String[] customId) {
        Party party = getParty(event, customId);
        if (party == null) return;

        long userId = event.getUser().getIdLong();

        PartyManager.leave(party, userId);

        PartyView view = PartyManager.createView(party);
        updateMessage(event, view);
    }
}
