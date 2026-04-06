package io.github.mosps.jda.response;

import io.github.mosps.ui.render.RenderResult;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.modals.Modal;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class SelectMenuResponder implements Responder {

    private final StringSelectInteractionEvent event;

    public SelectMenuResponder(StringSelectInteractionEvent event) {
        this.event = event;
    }

    @Override
    public void update(RenderResult render, String messageId) {
        event.getChannel().asTextChannel().editMessageById(
                messageId, render.getMessageEditData()
        ).setComponents(
                render.getComponents()
        ).queue();
    }

    @Override
    public void update(RenderResult render) {
        event.getHook().editOriginal(
                render.getMessageEditData()
        ).setComponents(
                render.getComponents()
        ).queue();
    }

    @Override
    public void reply(RenderResult render, Consumer<Message> callback) {
        event.getHook().sendMessage(
                MessageCreateData.fromEditData(render.getMessageEditData())
        ).setComponents(
                render.getComponents()
        ).queue(callback);
    }

    @Override
    public void reply(RenderResult render) {
        event.getHook().sendMessage(
                MessageCreateData.fromEditData(render.getMessageEditData())
        ).setComponents(
                render.getComponents()
        ).queue();
    }

    @Override
    public void openModal(Modal modal) {
        throw new UnsupportedOperationException("SelectMenuResponder cannot open Modal");
    }

    @Override
    public void ephemeral(String message, int seconds) {
        event.getHook().sendMessage(message)
                .setEphemeral(true)
                .queue(msg -> {
                    if (seconds != 0) msg.delete().queueAfter(seconds, TimeUnit.SECONDS,
                            success -> {},
                            error -> {});
                });
    }

    @Override
    public void ephemeral(RenderResult render, int seconds) {
        event.getHook().sendMessage(
                MessageCreateData.fromEditData(render.getMessageEditData())
        ).setComponents(
                render.getComponents()
        ).setEphemeral(true).queue(msg -> {
            if (seconds != 0) msg.delete().queueAfter(seconds, TimeUnit.SECONDS,
                    success -> {},
                    error -> {});
        });
    }

    @Override
    public void error(String message) {
        if (!event.isAcknowledged()) {
            event.reply(message)
                    .setEphemeral(true)
                    .queue(msg -> {
                        msg.deleteOriginal().queueAfter(5, TimeUnit.SECONDS,
                                success -> {},
                                error -> {});
                    });
        } else {
            event.getHook()
                    .sendMessage(message)
                    .setEphemeral(true)
                    .queue(msg -> {
                        msg.delete().queueAfter(5, TimeUnit.SECONDS,
                                success -> {},
                                error -> {});
                    });
        }
    }

    @Override
    public void delete() {
        event.getHook().deleteOriginal().queue();
    }

    @Override
    public void delete(String messageId) {
        event.getHook().deleteMessageById(messageId).queue();
    }
}
