package io.github.mosps.handlers.actions.profile.imagine;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.handlers.actions.data.SelectMenuData;
import io.github.mosps.mapper.ViewMapper;
import io.github.mosps.profile.imagine.ImagineEditField;
import io.github.mosps.profile.imagine.ImagineEditManager;
import io.github.mosps.profile.imagine.ImagineEditSession;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.profile.imagine.ImagineEditView;

public class ImagineEditAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        ImagineEditSession session = ImagineEditManager.get(context.getUserId());

        SelectMenuData data = context.isEmptyData()
                ? null
                : context.getData(SelectMenuData.class);

        ImagineEditField field = ImagineEditField.fromId(context.getCustomId().get("type"));
        if (field != null && data != null) {
            data.get().forEach(value -> field.apply(session, value));
        }

        ImagineEditView view = ViewMapper.map(session);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render);
    }
}
