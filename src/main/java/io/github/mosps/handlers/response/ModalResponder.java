package io.github.mosps.handlers.response;

import io.github.mosps.render.RenderResult;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

public class ModalResponder implements Responder {

    private final InteractionHook hook;

    public ModalResponder(ModalInteractionEvent event) {
        this.hook = event.getHook();
    }

    @Override
    public void update(RenderResult render) {
        hook.editOriginal(
                render.getMessageEditData()
        ).setComponents(
                render.getComponents()
        ).queue();
    }

    @Override
    public void reply(RenderResult render) {
        hook.sendMessage(
                MessageCreateData.fromEditData(render.getMessageEditData())
        ).setComponents(
                render.getComponents()
        ).queue();
    }

    @Override
    public void ephemeral(String message) {
        hook.sendMessage(message)
                .setEphemeral(true)
                .queue();
    }
}
