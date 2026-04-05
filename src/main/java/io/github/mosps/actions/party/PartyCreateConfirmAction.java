package io.github.mosps.actions.party;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.actions.data.ModalData;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.model.party.Party;
import io.github.mosps.model.party.PartyManager;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.party.PartyView;

public class PartyCreateConfirmAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.createParty(context.getUserId());

        ModalData data = context.getData(ModalData.class);

        party.setDestination(data.get("destination"));
        party.setTime(data.get("time"));
        party.setNote(data.get("note"));

        PartyView view = ViewMapper.map(party);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withReply(render)
                .withEphemeral("パーティを作成しました！", 5);
    }
}
