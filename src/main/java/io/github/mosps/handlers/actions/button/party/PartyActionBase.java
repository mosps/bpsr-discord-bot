package io.github.mosps.handlers.actions.button.party;

import io.github.mosps.handlers.handler.ButtonHandler;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import io.github.mosps.views.party.PartyView;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public abstract class PartyActionBase implements ButtonHandler {

    protected Party getParty(ButtonInteractionEvent event, String[] customId) {
        String partyId = customId[2];

        Party party = PartyManager.getParty(partyId);

        if (party == null) {
            event.reply("期限切れの募集です。").setEphemeral(true).queue();
            return null;
        }
        if (party.isClosed()) {
            event.reply("締め切られた募集です。").setEphemeral(true).queue();
            return null;
        }

        return party;
    }

    protected void updateMessage(ButtonInteractionEvent event, PartyView view) {
        RenderResult renderResult = MessageRenderer.render(view);

        event.getHook().editOriginal(
                renderResult.getMessageEditData()
        ).setComponents(
                renderResult.getComponents()
        ).queue();
    }
}
