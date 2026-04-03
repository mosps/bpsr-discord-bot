package io.github.mosps.handlers.actions.party.setting;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.handlers.actions.data.ModalData;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;

public class PartyEditConfirmAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.getParty(context.getCustomId().get("partyId"));
        if (party == null) {
            return ActionResult.of()
                    .error("このパーティは期限切れです。");
        }

        if (party.isClosed()) {
            return ActionResult.of()
                    .error("このパーティは締め切り済みです。");
        }

        if (context.getUserId() != party.getOwnerId()) {
            return ActionResult.of()
                    .error("パーティ作成者ではありません。");
        }

        ModalData data = context.getData(ModalData.class);

        party.setDestination(data.get("destination"));
        party.setTime(data.get("time"));
        party.setNote(data.get("note"));

        PartyView view = PartyManager.createView(party);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render).targetId(context.getCustomId().get("messageId"))
                .withEphemeral("パーティ設定を変更しました。");
    }
}
