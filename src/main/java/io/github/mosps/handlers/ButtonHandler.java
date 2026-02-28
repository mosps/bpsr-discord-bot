package io.github.mosps.handlers;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public interface ButtonHandler {
    boolean handle(ButtonInteractionEvent event);
}
