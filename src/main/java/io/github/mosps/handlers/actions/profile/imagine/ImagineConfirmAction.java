package io.github.mosps.handlers.actions.profile.imagine;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.profile.Profile;
import io.github.mosps.profile.ProfileManager;
import io.github.mosps.profile.imagine.ImagineEditManager;
import io.github.mosps.profile.imagine.ImagineEditSession;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.profile.ProfileView;

public class ImagineConfirmAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Profile profile = ProfileManager.getProfile(context.getUserId());
        ImagineEditSession session = ImagineEditManager.get(context.getUserId());

        session.getAddImagines().forEach(profile::addImagine);
        session.getRemoveImagines().keySet().forEach(profile::removeImagine);

        ProfileManager.saveProfile(profile);
        ImagineEditManager.remove(context.getUserId());

        ProfileView view = ProfileManager.createView(profile);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render);
    }
}
