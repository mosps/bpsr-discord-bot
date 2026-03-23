package io.github.mosps.handlers.actions.profile;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.profile.Profile;
import io.github.mosps.profile.ProfileField;
import io.github.mosps.profile.ProfileManager;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.profile.ProfileView;

public class ProfileRegisterAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Profile profile = ProfileManager.getProfile(context.getUserId());

        profile.setName(context.getName());

        ProfileField field = ProfileField.fromId(context.getType());
        if (field == null) {
            return ActionResult.of();
        }

        field.reset(profile);
        context.getValue().forEach(value -> field.apply(profile, value));

        ProfileManager.saveProfile(profile);

        ProfileView view = ProfileManager.createView(profile);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render);
    }
}
