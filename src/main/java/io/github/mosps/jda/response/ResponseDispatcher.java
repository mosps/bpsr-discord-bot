package io.github.mosps.jda.response;

import io.github.mosps.actions.ActionResult;

public class ResponseDispatcher {

    public static void dispatch(Responder responder, ActionResult result) {
        if (result.getUpdate() != null && result.getTargetId() != null) {
            responder.update(result.getUpdate(), result.getTargetId());
        }
        else if (result.getUpdate() != null) {
            responder.update(result.getUpdate());
        }
        if (result.getReply() != null) {
            responder.reply(result.getReply());
        }
        if (result.getModal() != null) {
            responder.openModal(result.getModal());
        }
        if (result.getEphemeralMessage() != null) {
            responder.ephemeral(result.getEphemeralMessage(), result.getSeconds());
        }
        if (result.getEphemeralRender() != null) {
            responder.ephemeral(result.getEphemeralRender(), result.getSeconds());
        }
        if (result.getErrorMessage() != null) {
            responder.error(result.getErrorMessage());
        }
        if (result.isDeleteSource()) {
            responder.delete();
        }
        if (result.getTargetId() != null) {
            for (String messageId : result.getDeleteMessageWithId()) {
                responder.delete(messageId);
            }
        }
    }
}
