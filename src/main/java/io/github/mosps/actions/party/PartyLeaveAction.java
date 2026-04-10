package io.github.mosps.actions.party;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.model.party.result.LeaveResult;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.model.party.Party;
import io.github.mosps.model.party.PartyManager;
import io.github.mosps.model.profile.ProfileManager;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.party.PartyView;

public class PartyLeaveAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.getParty(context.getGuildId(), context.getCustomId().getLong("partyId"));
        if (party == null) {
            return ActionResult.of()
                    .error("このパーティは期限切れです。");
        }

        LeaveResult result = PartyManager.leave(party, context.getUserId());
        if (result != LeaveResult.SUCCESS) {
            return ActionResult.of().error(result.getMessage());
        }

        PartyManager.saveParty(context.getGuildId(), party);

        PartyView view = ViewMapper.map(party, PartyView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render)
                .withEphemeral("パーティから退出しました", 5);
    }
}
