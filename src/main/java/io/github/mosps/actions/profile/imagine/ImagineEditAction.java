package io.github.mosps.actions.profile.imagine;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.actions.data.SelectMenuData;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.model.profile.imagine.ImagineEditField;
import io.github.mosps.model.profile.imagine.ImagineEditManager;
import io.github.mosps.model.profile.imagine.ImagineEditSession;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.profile.imagine.ImagineEditView;

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

        ImagineEditView view = ViewMapper.map(session, ImagineEditView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render);
    }
}
