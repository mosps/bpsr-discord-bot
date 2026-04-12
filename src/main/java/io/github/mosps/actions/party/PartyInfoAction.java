package io.github.mosps.actions.party;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.model.party.Party;
import io.github.mosps.model.party.PartyManager;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.party.PartyInfoView;

public class PartyInfoAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.getParty(context.getGuildId(), context.getCustomId().getLong("partyId"));
        if (party == null) {
            return ActionResult.of()
                    .error("このパーティは期限切れです。");
        }

        PartyInfoView view = ViewMapper.map(party, PartyInfoView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withEphemeral(render, 10 * 60);
    }
}
