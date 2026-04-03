package io.github.mosps.handlers.actions.profile;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.profile.Profile;
import io.github.mosps.profile.ProfileManager;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.profile.ProfileView;

public class ProfileCreateAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Profile profile = ProfileManager.getOrCreateProfile(context.getUserId());

        profile.setName(context.getName());

        ProfileView view = ProfileManager.createView(profile);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withReply(render)
                .withEphemeral("プロフィールを作成しました！");
    }
}
