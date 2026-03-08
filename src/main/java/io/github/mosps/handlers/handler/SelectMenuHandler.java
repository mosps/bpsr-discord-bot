package io.github.mosps.handlers.handler;

import io.github.mosps.data.Classes;
import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionManager;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.handlers.response.Responder;
import io.github.mosps.handlers.response.ResponseDispatcher;
import io.github.mosps.handlers.response.SelectMenuResponder;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;

public class SelectMenuHandler {

    public static void handle(StringSelectInteractionEvent event) {
        String[] customId = event.getComponentId().split(":");
        String key = customId[0] + ":" + customId[1];
        String type = customId[2];

        ActionContext context = new ActionContext(event.getUser().getIdLong(), event.getUser().getEffectiveName(), type, event.getValues());
        Action action = ActionManager.get(key);

        ActionResult result = action.execute(context);

        Responder responder = new SelectMenuResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
