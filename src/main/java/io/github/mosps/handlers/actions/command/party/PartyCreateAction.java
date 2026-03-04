package io.github.mosps.handlers.actions.command.party;

import io.github.mosps.handlers.actions.button.party.PartyActionBase;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PartyCreateAction {

    public static void execute(SlashCommandInteractionEvent event) {
        long userId = event.getUser().getIdLong();

        Party party = PartyManager.createParty(userId);

        PartyView view = PartyManager.createView(party);
        RenderResult renderResult = MessageRenderer.render(view);

        event.getHook().editOriginal(
                renderResult.getMessageEditData()
        ).setComponents(
                renderResult.getComponents()
        ).queue();
    }
}
