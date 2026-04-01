package io.github.mosps.handlers.handler;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionManager;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.handlers.actions.data.EmptyData;
import io.github.mosps.handlers.response.ButtonResponder;
import io.github.mosps.handlers.response.Responder;
import io.github.mosps.handlers.response.ResponseDispatcher;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ButtonHandler {

    public static void handle(ButtonInteractionEvent event) {
        CustomId customId = CustomId.of(event.getComponentId());
        String messageId = event.getMessageId();
        long userId = event.getUser().getIdLong();
        String name = event.getUser().getEffectiveName();

        ActionContext context = new ActionContext(messageId, userId, name, customId, new EmptyData());
        Action action = ActionManager.get(customId.getKey());

        ActionResult result = action.execute(context);

        Responder responder = new ButtonResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
