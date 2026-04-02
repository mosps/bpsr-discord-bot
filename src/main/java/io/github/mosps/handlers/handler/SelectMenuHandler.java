package io.github.mosps.handlers.handler;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionManager;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.handlers.actions.data.SelectMenuData;
import io.github.mosps.handlers.response.Responder;
import io.github.mosps.handlers.response.ResponseDispatcher;
import io.github.mosps.handlers.response.SelectMenuResponder;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;

public class SelectMenuHandler {

    public static void handle(StringSelectInteractionEvent event) {
        CustomId customId = CustomId.of(event.getComponentId());
        String messageId = event.getMessageId();
        long userId = event.getUser().getIdLong();
        String name = event.getUser().getEffectiveName();

        if (!event.getUser().getId().equals(customId.get("ownerId")) && customId.getKey().startsWith("profile")) {
            event.getHook().sendMessage("あなたはこのメニューを操作できません。")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        ActionContext context = new ActionContext(messageId, userId, name, customId, new SelectMenuData(event.getValues()));
        Action action = ActionManager.get(customId.getKey());

        ActionResult result = action.execute(context);

        Responder responder = new SelectMenuResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
