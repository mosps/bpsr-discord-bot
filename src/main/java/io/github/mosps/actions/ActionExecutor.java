package io.github.mosps.actions;

import io.github.mosps.util.log.LogService;

public class ActionExecutor {

    public static ActionResult execute(ActionContext context) {
        Action action = ActionManager.get(context.getCustomId().getKey());

        ActionResult result = action.execute(context);
        LogService.log(context, result);

        return result;
    }
}
