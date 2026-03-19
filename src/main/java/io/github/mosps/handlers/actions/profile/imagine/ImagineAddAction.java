package io.github.mosps.handlers.actions.profile.imagine;

import io.github.mosps.data.Imagines;
import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.profile.imagine.ImagineEditManager;
import io.github.mosps.profile.imagine.ImagineEditSession;

public class ImagineAddAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        ImagineEditSession session = ImagineEditManager.get(context.getUserId());

        context.getValue().forEach(v ->
                session.add(Imagines.valueOf(v))
        );

        return
    }
}
