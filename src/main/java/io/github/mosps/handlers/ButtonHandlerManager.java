package io.github.mosps.handlers;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.util.HashMap;
import java.util.Map;

public class ButtonHandlerManager {

    private static final Map<String, ButtonHandler> handlers = new HashMap<>();

    static {
        register("party", new PartyButtonHandler());
    }

    public static void register(String prefix, ButtonHandler handler) {
        handlers.put(prefix, handler);
    }

    public static void handle(ButtonInteractionEvent event) {
        String[] customId = event.getComponentId().split(":");

        ButtonHandler handler = handlers.get(customId[0]);

        if (handler != null) {
            handler.handle(event, customId);
        }
    }
}
