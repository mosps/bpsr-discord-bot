package io.github.mosps.handlers.actions.party;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import io.github.mosps.party.PartyRoleManager;
import io.github.mosps.profile.Profile;
import io.github.mosps.profile.ProfileManager;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;

public class PartyJoinAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.getParty(context.getCustomId().get("partyId"));
        if (party == null) {
            return ActionResult.of()
                    .withEphemeral("このパーティは期限切れです。");
        }

        if (party.isClosed()) {
            return ActionResult.of()
                    .withEphemeral("このパーティは締め切り済みです。");
        }

        Profile profile = ProfileManager.getProfile(context.getUserId());
        if (profile == null) {
            return ActionResult.of()
                    .withEphemeral("プロフィールを作成してください。");
        }

        if (!PartyRoleManager.canJoin(party.getPreset(), party, profile)) {
            return ActionResult.of()
                    .withEphemeral("このパーティは満員です。");
        }

        PartyManager.join(party, context.getUserId());

        PartyView view = PartyManager.createView(party);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render)
                .withEphemeral("パーティに参加しました");
    }
}
