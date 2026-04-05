package io.github.mosps.actions;

import io.github.mosps.ui.render.RenderResult;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.modals.Modal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ActionResult {

    private RenderResult update;
    private String targetId;
    private RenderResult reply;
    private Modal modal;
    private String ephemeralMessage;
    private RenderResult ephemeralRender;
    private int seconds;
    private String errorMessage;
    private List<String> deleteMessageWithId = new ArrayList<>();
    private boolean deleteSource;
    private Consumer<Message> afterReply;

    private ActionResult() {}

    public static ActionResult of() {
        return new ActionResult();
    }

    public ActionResult withUpdate(RenderResult render) {
        this.update = render;
        return this;
    }

    public ActionResult targetId(String messageId) {
        this.targetId = messageId;
        return this;
    }

    public ActionResult withReply(RenderResult render) {
        this.reply = render;
        return this;
    }

    public ActionResult withModal(Modal modal) {
        this.modal = modal;
        return this;
    }

    public ActionResult withEphemeral(String message, int seconds) {
        this.ephemeralMessage = message;
        this.seconds = seconds;
        return this;
    }

    public ActionResult withEphemeral(RenderResult result, int seconds) {
        this.ephemeralRender = result;
        this.seconds = seconds;
        return this;
    }

    public ActionResult error(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public ActionResult deleteSource() {
        this.deleteSource = true;
        return this;
    }

    public ActionResult deleteAll(String... messageIds) {
        this.deleteMessageWithId.addAll(List.of(messageIds));
        return this;
    }

    public ActionResult afterReply(Consumer<Message> consumer) {
        this.afterReply = consumer;
        return this;
    }

    public RenderResult getUpdate() {
        return update;
    }

    public String getTargetId() {
        return targetId;
    }

    public RenderResult getReply() {
        return reply;
    }

    public Modal getModal() {
        return modal;
    }

    public String getEphemeralMessage() {
        return ephemeralMessage;
    }

    public RenderResult getEphemeralRender() {
        return ephemeralRender;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isDeleteSource() {
        return deleteSource;
    }

    public List<String> getDeleteMessageWithId() {
        return deleteMessageWithId;
    }

    public Consumer<Message> getAfterReply() {
        return afterReply;
    }
}
