package io.github.mosps.actions.party.setting;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;

public class PartyDeleteDenyAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        return ActionResult.of().deleteSource().withEphemeral("パーティ削除をキャンセルしました。", 5);
    }
}
