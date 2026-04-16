package io.github.mosps.actions.profile;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.actions.data.SelectMenuData;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.ProfileField;
import io.github.mosps.model.profile.ProfileManager;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.profile.ProfileView;

public class ProfileRegisterAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Profile profile = ProfileManager.getProfile(context.getUserId());

        SelectMenuData data = context.getData(SelectMenuData.class);
        ProfileField field = ProfileField.fromId(context.getCustomId().get("type"));
        if (field == null) {
            return ActionResult.of().error("不明な変更です。");
        }

        field.reset(profile);
        data.get().forEach(value -> field.apply(profile, value));

        ProfileManager.saveProfile(profile);

        ProfileView view = ViewMapper.map(profile, ProfileView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render);
    }
}
