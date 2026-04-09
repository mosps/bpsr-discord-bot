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
        Party party = PartyManager.createParty(context.getGuildId(), context.getUserId());

        ModalData data = context.getData(ModalData.class);

        PartyManager.setSettings(party, data.get("destination"),  data.get("time"), data.get("note"));
        PartyManager.saveParty(context.getGuildId(), party);

        PartyView view = ViewMapper.map(party, PartyView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withReply(render)
                .afterReply(message -> {
                    party.setMessageId(message.getId());
                    PartyManager.saveParty(context.getGuildId(), party);
                })
                .withEphemeral("パーティを作成しました！", 5);
    }
}
