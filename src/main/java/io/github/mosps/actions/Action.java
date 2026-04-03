package io.github.mosps.actions;

public interface Action {
    ActionResult execute(ActionContext context);
}
