package io.github.mosps.handlers.actions;

import io.github.mosps.render.RenderResult;

public class ActionResult {

    private RenderResult update;
    private RenderResult reply;
    private String ephemeralMessage;

    private ActionResult() {}

    public static ActionResult of() {
        return new ActionResult();
    }

    public ActionResult withUpdate(RenderResult render) {
        this.update = render;
        return this;
    }

    public ActionResult withReply(RenderResult render) {
        this.reply = render;
        return this;
    }

    public ActionResult withEphemeral(String message) {
        this.ephemeralMessage = message;
        return this;
    }

    public RenderResult getUpdate() {
        return update;
    }

    public RenderResult getReply() {
        return reply;
    }

    public String getEphemeralMessage() {
        return ephemeralMessage;
    }
}
