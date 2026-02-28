package io.github.mosps.listeners;

import io.github.mosps.handlers.ButtonHandler;
import io.github.mosps.handlers.ButtonHandlerManager;
import io.github.mosps.handlers.PartyButtonHandler;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        ButtonHandlerManager.handle(event);
    }
}
