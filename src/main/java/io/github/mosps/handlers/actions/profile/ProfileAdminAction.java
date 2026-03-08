package io.github.mosps.handlers.actions.profile;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.profile.ProfileLobbyView;

public class ProfileAdminAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        if (context.getUserId() != 635395177946808321L) {
            return ActionResult.error()
                    .withEphemeral("このコマンドを実行する権限がありません。");
        }

        RenderResult render = MessageRenderer.render(new ProfileLobbyView());

        return ActionResult.success(render)
                .withEphemeral("プロフィール登録用メッセージを生成しました。");
    }
}
