package io.github.mosps.handlers.actions;

public interface Action {
    ActionResult execute(ActionContext context);
}
