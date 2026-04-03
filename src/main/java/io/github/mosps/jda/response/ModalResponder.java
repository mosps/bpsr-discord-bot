package io.github.mosps.jda.response;

import io.github.mosps.ui.render.RenderResult;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.modals.Modal;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import java.util.concurrent.TimeUnit;

public class ModalResponder implements Responder {

    private final ModalInteractionEvent event;

    public ModalResponder(ModalInteractionEvent event) {
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
    public void reply(RenderResult render) {
        event.getHook().sendMessage(
                MessageCreateData.fromEditData(render.getMessageEditData())
        ).setComponents(
                render.getComponents()
        ).queue();
    }

    @Override
    public void openModal(Modal modal) {
        throw new UnsupportedOperationException("ModalResponder cannot open Modal");
    }

    @Override
    public void ephemeral(String message) {
        event.getHook().sendMessage(message)
                .setEphemeral(true)
                .queue(msg -> {
                    msg.delete().queueAfter(5, TimeUnit.SECONDS);
                });
    }

    @Override
    public void ephemeral(RenderResult render) {
        event.getHook().sendMessage(
                MessageCreateData.fromEditData(render.getMessageEditData())
        ).setComponents(
                render.getComponents()
        ).setEphemeral(true).queue();
    }

    @Override
    public void error(String message) {
        if (!event.isAcknowledged()) {
            event.reply(message)
                    .setEphemeral(true)
                    .queue(msg -> {
                        msg.deleteOriginal().queueAfter(5, TimeUnit.SECONDS);
                    });
        } else {
            event.getHook()
                    .sendMessage(message)
                    .setEphemeral(true)
                    .queue(msg -> {
                        msg.delete().queueAfter(5, TimeUnit.SECONDS);
                    });
        }
    }
}
