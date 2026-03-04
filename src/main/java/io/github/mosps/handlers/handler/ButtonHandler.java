package io.github.mosps.handlers.handler;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionManager;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.handlers.response.ButtonResponder;
import io.github.mosps.handlers.response.Responder;
import io.github.mosps.handlers.response.ResponseDispatcher;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ButtonHandler {

    public static void handle(ButtonInteractionEvent event) {
        String[] customId = event.getComponentId().split(":");
        String key = customId[0] + ":" + customId[1];
        String partyId = customId[2];

        ActionContext context = new ActionContext(event.getUser().getIdLong(), partyId);
        Action action = ActionManager.get(key);

        ActionResult result = action.execute(context);

        Responder responder = new ButtonResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
