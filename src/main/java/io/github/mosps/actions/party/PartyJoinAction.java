package io.github.mosps.actions.party;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.model.party.Party;
import io.github.mosps.model.party.PartyManager;
import io.github.mosps.model.party.PartyRoleManager;
import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.ProfileManager;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.party.PartyView;

public class PartyJoinAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.getParty(context.getGuildId(), context.getCustomId().getLong("partyId"));
        if (party == null) {
            return ActionResult.of()
                    .error("このパーティは期限切れです。");
        }

        if (party.isClosed()) {
            return ActionResult.of()
                    .error("このパーティは締め切り済みです。");
        }

        Profile profile = ProfileManager.getProfile(context.getGuildId(), context.getUserId());
        if (profile == null) {
            return ActionResult.of()
                    .error("プロフィールを作成してください。");
        }
        if (profile.getMainClass() == null) {
            return ActionResult.of()
                    .error("メインクラスを設定してください。");
        }

        if (!PartyManager.tryJoin(context.getGuildId(), party, profile, context.getUserId())) {
            return ActionResult.of()
                    .error("このパーティは満員です。");
        }
        PartyManager.saveParty(context.getGuildId(), party);

        PartyView view = ViewMapper.map(party, PartyView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render)
                .withEphemeral("パーティに参加しました", 5);
    }
}
