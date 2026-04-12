package io.github.mosps.actions.profile;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.ProfileManager;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.render.util.PageManager;
import io.github.mosps.ui.views.profile.ProfileView;

public class ProfilePagePrevAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Profile profile = ProfileManager.getProfile(context.getUserId());

        if (PageManager.hasPrev(profile.getPage())) {
            profile.prevPage();
        }

        ProfileManager.saveProfile(profile);

        ProfileView view = ViewMapper.map(profile, ProfileView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render);
    }
}
