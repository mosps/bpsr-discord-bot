package io.github.mosps.actions.profile;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.profile.ProfileLobbyView;

public class ProfileAdminAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        if (context.getUserId() != 252823777493123072L && context.getUserId() != 635395177946808321L) {
            return ActionResult.of()
                    .error("このコマンドを実行する権限がありません。");
        }

        RenderResult render = MessageRenderer.render(new ProfileLobbyView());

        return ActionResult.of().withUpdate(render)
                .withEphemeral("プロフィール登録用メッセージを生成しました。", 5);
    }
}
