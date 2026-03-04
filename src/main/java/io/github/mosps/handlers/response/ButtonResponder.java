package io.github.mosps.handlers.response;

import io.github.mosps.render.RenderResult;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;

public class ButtonResponder implements Responder {

    private final InteractionHook hook;

    public ButtonResponder(ButtonInteractionEvent event) {
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
    public void ephemeral(String message) {
        hook.sendMessage(message)
                .setEphemeral(true)
                .queue();
    }
}
