package io.github.mosps.handlers.handler;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionManager;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.handlers.response.ButtonResponder;
import io.github.mosps.handlers.response.ModalResponder;
import io.github.mosps.handlers.response.Responder;
import io.github.mosps.handlers.response.ResponseDispatcher;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public class ModalHandler {

    public static void handle(ModalInteractionEvent event) {
        CustomId customId = CustomId.of(event.getModalId());
        long userId = event.getUser().getIdLong();
        String name = event.getUser().getEffectiveName();

        ActionContext context = new ActionContext(userId, name, customId, event.);
        Action action = ActionManager.get(customId.getKey());

        ActionResult result = action.execute(context);

        Responder responder = new ModalResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
