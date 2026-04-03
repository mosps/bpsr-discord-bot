package io.github.mosps.jda.handler;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionManager;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.actions.data.EmptyData;
import io.github.mosps.jda.response.ButtonResponder;
import io.github.mosps.jda.response.Responder;
import io.github.mosps.jda.response.ResponseDispatcher;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ButtonHandler {

    public static void handle(ButtonInteractionEvent event) {
        CustomId customId = CustomId.of(event.getComponentId());
        String messageId = event.getMessageId();
        long userId = event.getUser().getIdLong();
        String name = event.getMember() == null
                ? event.getUser().getEffectiveName()
                : event.getMember().getEffectiveName();

        ActionContext context = new ActionContext(messageId, userId, name, customId, new EmptyData());
        Action action = ActionManager.get(customId.getKey());

        ActionResult result = action.execute(context);

        Responder responder = new ButtonResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
