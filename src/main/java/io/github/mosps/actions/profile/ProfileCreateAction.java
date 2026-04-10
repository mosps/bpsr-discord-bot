package io.github.mosps.actions.profile;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.ProfileManager;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.profile.ProfileView;

public class ProfileCreateAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Profile profile = ProfileManager.getOrCreateProfile(context.getGuildId(), context.getUserId());

        profile.setName(context.getName());//これいる？
        ProfileManager.saveProfile(profile);

        ProfileView view = ViewMapper.map(profile, ProfileView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of()
                .withEphemeral(render, 30 * 60);
    }
}
