package io.github.mosps.listeners;

import io.github.mosps.handlers.ButtonHandler;
import io.github.mosps.handlers.PartyButtonHandler;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageButtonListener extends ListenerAdapter {
    private final List<ButtonHandler> handlers = List.of(
            new PartyButtonHandler()
    );

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        for (ButtonHandler handler : handlers) {
            if (handler.handle(event)) return;
        }
    }
}
