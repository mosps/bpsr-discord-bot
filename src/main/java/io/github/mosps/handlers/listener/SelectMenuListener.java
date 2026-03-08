package io.github.mosps.handlers.listener;

import io.github.mosps.handlers.handler.ButtonHandler;
import io.github.mosps.handlers.handler.SelectMenuHandler;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.event.ActionListener;

public class SelectMenuListener extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if (!event.isAcknowledged()) {
            event.deferEdit().queue();
        }
        SelectMenuHandler.handle(event);
    }
}
