package io.github.mosps.handlers.actions;

import io.github.mosps.render.RenderResult;

public class ActionResult {

    private RenderResult update;
    private String ephemeralMessage;

    private ActionResult() {}

    public static ActionResult error() {
        return new ActionResult();
    }

    public static ActionResult success(RenderResult render) {
        ActionResult result = new ActionResult();
        result.update = render;
        return result;
    }

    public ActionResult withEphemeral(String message) {
        this.ephemeralMessage = message;
        return this;
    }

    public RenderResult getUpdate() {
        return update;
    }

    public String getEphemeralMessage() {
        return ephemeralMessage;
    }
}
