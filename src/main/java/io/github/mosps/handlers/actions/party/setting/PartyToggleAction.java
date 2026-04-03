package io.github.mosps.handlers.actions.party.setting;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;

public class PartyToggleAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.getParty(context.getCustomId().get("partyId"));
        if (party == null) {
            return ActionResult.of()
                    .error("このパーティは期限切れです。");
        }

        if (context.getUserId() != party.getOwnerId()) {
            return ActionResult.of()
                    .error("パーティ作成者ではありません。");
        }

        PartyManager.toggle(party);

        PartyView view = PartyManager.createView(party);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render).targetId(context.getCustomId().get("messageId"))
                .withEphemeral(party.isClosed()
                        ? "募集を締め切りました"
                        : "募集を再開しました");
    }
}
