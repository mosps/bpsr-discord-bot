package io.github.mosps.actions;

public class ActionExecutor {

    public static ActionResult execute(ActionContext context) {
        Action action = ActionManager.get(context.getCustomId().getKey());

        ActionResult result = action.execute(context);

        return result;
    }
}
