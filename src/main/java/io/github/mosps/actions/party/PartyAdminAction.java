package io.github.mosps.actions.party;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.party.PartyLobbyView;

public class PartyAdminAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        if (context.getUserId() != 635395177946808321L) {
            return ActionResult.of()
                    .error("このコマンドを実行する権限がありません。");
        }

        RenderResult render = MessageRenderer.render(new PartyLobbyView());

        return ActionResult.of().withUpdate(render)
                .withEphemeral("パーティ作成用メッセージを生成しました。");
    }
}
