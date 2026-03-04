package io.github.mosps.handlers.handler;

import io.github.mosps.handlers.actions.button.party.PartyCloseAction;
import io.github.mosps.handlers.actions.button.party.PartyJoinAction;
import io.github.mosps.handlers.actions.button.party.PartyLeaveAction;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.util.HashMap;
import java.util.Map;

public class ButtonHandlerManager {

    private static final Map<String, ButtonHandler> handlers = new HashMap<>();

    static {
        register("party:join", new PartyJoinAction());
        register("party:leave", new PartyLeaveAction());
        register("party:close", new PartyCloseAction());
    }

    public static void register(String prefix, ButtonHandler handler) {
        handlers.put(prefix, handler);
    }

    public static void handle(ButtonInteractionEvent event) {
        String[] customId = event.getComponentId().split(":");
        String key = customId[0] + ":" + customId[1];

        ButtonHandler handler = handlers.get(key);

        if (handler != null) {
            handler.handle(event, customId);
        }
    }
}
