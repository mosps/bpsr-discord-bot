package io.github.mosps.handlers.actions.profile.imagine;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.mapper.ViewMapper;
import io.github.mosps.profile.imagine.ImagineEditManager;
import io.github.mosps.profile.imagine.ImagineEditSession;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.profile.imagine.ImagineEditView;

public class ImaginePagePrevAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        ImagineEditSession session = ImagineEditManager.get(context.getUserId());

        if (session.getPage() > 0) {
            session.prevPage();
        }

        ImagineEditView view = ViewMapper.map(session);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render);
    }
}
