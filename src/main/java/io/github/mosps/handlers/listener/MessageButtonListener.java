package io.github.mosps.handlers.listener;

import io.github.mosps.handlers.handler.ButtonHandler;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MessageButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        CustomId customId = CustomId.of(event.getComponentId());
        boolean isModal = customId.getKey().equals("party:edit") || customId.getKey().equals("party:create");

        if (!event.isAcknowledged() && !isModal) {
            event.deferEdit().queue();
        }

        ButtonHandler.handle(event);
    }
}
