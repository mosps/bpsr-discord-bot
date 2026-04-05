package io.github.mosps.actions.party.setting;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.model.party.Party;
import io.github.mosps.model.party.PartyManager;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.party.setting.PartySettingView;

public class PartySettingAction implements Action {

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

        PartySettingView view = ViewMapper.map(party);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of()
                .withEphemeral(render, 30 * 60);
    }
}
