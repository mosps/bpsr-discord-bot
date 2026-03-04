package io.github.mosps.handlers.actions.party;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;

public class PartyCloseAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.getParty(context.getPartyId());
        if (party == null) {
            return ActionResult.error()
                    .withEphemeral("このパーティは期限切れです。");
        }

        if (party.isClosed()) {
            return ActionResult.error()
                    .withEphemeral("このパーティは締め切り済みです。");
        }

        if (context.getUserId() != party.getOwnerId()) {
            return ActionResult.error()
                    .withEphemeral("パーティ作成者ではありません。");
        }

        PartyManager.close(party);

        PartyView view = PartyManager.createView(party);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.success(render)
                .withEphemeral("パーティを締め切りました。");
    }
}
