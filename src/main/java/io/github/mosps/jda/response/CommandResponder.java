package io.github.mosps.jda.response;

import io.github.mosps.ui.render.RenderResult;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.modals.Modal;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import java.util.concurrent.TimeUnit;

public class CommandResponder implements Responder {

    private final SlashCommandInteractionEvent event;

    public CommandResponder(SlashCommandInteractionEvent event) {
        this.event = event;
    }

    @Override
    public void update(RenderResult render, String messageId) {
        throw new UnsupportedOperationException("CommandResponder cannot update message by messageId");
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
        throw new UnsupportedOperationException("CommandResponder cannot open Modal");
    }

    @Override
    public void ephemeral(String message, int seconds) {
        event.getHook().sendMessage(message)
                .setEphemeral(true)
                .queue(msg -> {
                    if (seconds != 0) msg.delete().queueAfter(seconds, TimeUnit.SECONDS);
                });
    }

    @Override
    public void ephemeral(RenderResult render, int seconds) {
        event.getHook().sendMessage(
                MessageCreateData.fromEditData(render.getMessageEditData())
        ).setComponents(
                render.getComponents()
        ).setEphemeral(true).queue(msg -> {
            if (seconds != 0) msg.delete().queueAfter(seconds, TimeUnit.SECONDS);
        });
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

    @Override
    public void delete() {
        throw new UnsupportedOperationException("CommandResponder cannot delete Message");
    }

    @Override
    public void delete(String messageId) {
        throw new UnsupportedOperationException("CommandResponder cannot delete Message");
    }
}
