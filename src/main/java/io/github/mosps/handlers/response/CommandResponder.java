package io.github.mosps.handlers.response;

import io.github.mosps.render.RenderResult;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.modals.Modal;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

public class CommandResponder implements Responder {

    private final SlashCommandInteractionEvent event;

    public CommandResponder(SlashCommandInteractionEvent event) {
        this.event = event;
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
        event.replyModal(modal).queue();
    }

    @Override
    public void ephemeral(String message) {
        event.getHook().sendMessage(message)
                .setEphemeral(true)
                .queue();
    }
}
