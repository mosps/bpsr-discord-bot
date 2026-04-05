package io.github.mosps.actions.party.setting;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.actions.data.SelectMenuData;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.model.party.Party;
import io.github.mosps.model.party.PartyManager;
import io.github.mosps.model.party.PartyRolePreset;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.party.PartyView;

public class PartyRoleSettingAction implements Action {

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

        SelectMenuData data = context.getData(SelectMenuData.class);
        String selected = data.get().getFirst();

        party.setPreset(PartyRolePreset.valueOf(selected));
        PartyManager.saveParty(party);

        PartyView view = ViewMapper.map(party, PartyView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render).targetId(party.getMessageId());
    }
}
