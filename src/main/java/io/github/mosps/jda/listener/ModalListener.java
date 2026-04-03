package io.github.mosps.jda.listener;

import io.github.mosps.jda.handler.ModalHandler;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ModalListener extends ListenerAdapter {

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if (!event.isAcknowledged()) {
            event.deferEdit().queue();
        }
        ModalHandler.handle(event);
    }
}
