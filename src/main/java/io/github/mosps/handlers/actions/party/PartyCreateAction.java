package io.github.mosps.handlers.actions.party;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PartyCreateAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.createParty(context.getUserId());

        PartyView view = PartyManager.createView(party);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.success(render)
                .withEphemeral("パーティを作成しました！");
    }
}
