package io.github.mosps.actions.profile.imagine;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.model.profile.imagine.ImagineEditManager;
import io.github.mosps.model.profile.imagine.ImagineEditSession;
import io.github.mosps.ui.mapper.ViewMapper;
import io.github.mosps.ui.render.MessageRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.render.util.PageManager;
import io.github.mosps.ui.views.profile.imagine.ImagineEditView;

public class ImagineRemovePagePrevAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        ImagineEditSession session = ImagineEditManager.get(context.getUserId());
        if (session == null) {
            return ActionResult.of().error("このセッションは期限切れです。");
        }

        if (PageManager.hasPrev(session.getRemoveImaginePage())) {
            session.prevRemoveImaginePage();
        }

        ImagineEditView view = ViewMapper.map(session, ImagineEditView.class);
        RenderResult render = MessageRenderer.render(view);

        return ActionResult.of().withUpdate(render);
    }
}
