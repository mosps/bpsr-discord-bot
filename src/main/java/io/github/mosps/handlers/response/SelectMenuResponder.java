package io.github.mosps.handlers.response;

import io.github.mosps.render.RenderResult;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.modals.Modal;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

public class SelectMenuResponder implements Responder {

    private final StringSelectInteractionEvent event;

    public SelectMenuResponder(StringSelectInteractionEvent event) {
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

    @Override
    public void ephemeral(RenderResult render) {
        event.getHook().sendMessage(
                MessageCreateData.fromEditData(render.getMessageEditData())
        ).setComponents(
                render.getComponents()
        ).setEphemeral(true).queue();
    }
}
