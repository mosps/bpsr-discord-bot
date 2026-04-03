package io.github.mosps.jda.listener;

import io.github.mosps.jda.handler.SelectMenuHandler;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SelectMenuListener extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if (!event.isAcknowledged()) {
            event.deferEdit().queue();
        }
        SelectMenuHandler.handle(event);
    }
}
