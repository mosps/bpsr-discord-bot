package io.github.mosps.actions.party;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
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
        Party party = PartyManager.getParty(context.getCustomId().get("partyId"));
        if (party == null) {
            return ActionResult.of()
                    .error("このパーティは期限切れです。");
        }

        if (party.isClosed()) {
            return ActionResult.of()
                    .error("このパーティは締め切り済みです。");
        }

        if (!party.getMembers().contains(context.getUserId())) {
            return ActionResult.of()
                    .error("パーティに参加していません。");
        }

        if (ProfileManager.getProfile(context.getUserId()) == null) {
            return ActionResult.of()
                    .error("プロフィールを作成してください。");
        }

        PartyManager.leave(party, context.getUserId());
        PartyManager.saveParty(party);

        PartyView view = ViewMapper.map(party, PartyView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render)
                .withEphemeral("パーティから退出しました", 5);
    }
}
