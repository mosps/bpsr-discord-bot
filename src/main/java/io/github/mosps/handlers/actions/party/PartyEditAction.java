package io.github.mosps.handlers.actions.party;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.handlers.actions.data.ModalData;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;

public class PartyEditAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.createParty(context.getUserId());

        ModalData data = context.getData(ModalData.class);

        party.setDestination(data.get("destination"));
        party.setTime(data.get("time"));
        party.setNote(data.get("note"));

        PartyView view = PartyManager.createView(party);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withReply(render)
                .withEphemeral("パーティ設定を変更しました。");
    }
}
