package io.github.mosps.jda.handler;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionManager;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.actions.data.SelectMenuData;
import io.github.mosps.jda.response.Responder;
import io.github.mosps.jda.response.ResponseDispatcher;
import io.github.mosps.jda.response.SelectMenuResponder;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;

public class SelectMenuHandler extends Handler {

    public static void handle(StringSelectInteractionEvent event) {
        CustomId customId = CustomId.of(event.getComponentId());
        long guildId = requireGuildId(event);
        String messageId = event.getMessageId();
        long userId = event.getUser().getIdLong();
        String name = event.getMember() == null
                ? event.getUser().getEffectiveName()
                : event.getMember().getEffectiveName();

        if (!event.getUser().getId().equals(customId.get("ownerId")) && customId.getKey().startsWith("profile")) {
            event.getHook().sendMessage("あなたはこのメニューを操作できません。")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        ActionContext context = new ActionContext(guildId, messageId, userId, name, customId, new SelectMenuData(event.getValues()));
        Action action = ActionManager.get(customId.getKey());

        ActionResult result = action.execute(context);

        Responder responder = new SelectMenuResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
