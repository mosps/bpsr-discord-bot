package io.github.mosps.handlers.actions.profile.imagine;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
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

        ImagineEditField field = ImagineEditField.fromId(context.getType());
        if (field != null) {
            context.getValue().forEach(value -> field.apply(session, value));
        }

        ImagineEditView view = ImagineEditManager.createView(session);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.success(render);
    }
}
