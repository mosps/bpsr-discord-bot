package io.github.mosps.handlers.response;

import io.github.mosps.handlers.actions.ActionResult;

public class ResponseDispatcher {

    public static void dispatch(Responder responder, ActionResult result) {
        if (result.getUpdate() != null) {
            responder.update(result.getUpdate());
        }
        if (result.getReply() != null) {
            responder.reply(result.getReply());
        }
        if (result.getEphemeralMessage() != null) {
            responder.ephemeral(result.getEphemeralMessage());
        }
    }
}
