package io.github.mosps.actions.profile.imagine;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.ProfileManager;
import io.github.mosps.model.profile.imagine.ImagineEditManager;
import io.github.mosps.model.profile.imagine.ImagineEditSession;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.profile.ProfileView;

public class ImagineConfirmAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Profile profile = ProfileManager.getProfile(context.getGuildId(), context.getUserId());
        ImagineEditSession session = ImagineEditManager.get(context.getGuildId(), context.getUserId());

        session.getAddImagines().forEach(profile::addImagine);
        session.getRemoveImagines().keySet().forEach(profile::removeImagine);

        ProfileManager.stillOwnedImagines(profile);

        ProfileManager.saveProfile(profile);
        ImagineEditManager.remove(context.getUserId());

        ProfileView view = ViewMapper.map(profile, ProfileView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render);
    }
}
