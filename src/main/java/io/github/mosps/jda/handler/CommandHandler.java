package io.github.mosps.jda.handler;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionManager;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.actions.data.EmptyData;
import io.github.mosps.jda.response.CommandResponder;
import io.github.mosps.jda.response.Responder;
import io.github.mosps.jda.response.ResponseDispatcher;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandHandler {

    public static void handle(SlashCommandInteractionEvent event) {
        CustomId customId = CustomId.of(event.getName() + ":" + event.getSubcommandName());
        long userId = event.getUser().getIdLong();
        String name = event.getMember() == null
                ? event.getUser().getEffectiveName()
                : event.getMember().getEffectiveName();

        ActionContext context = new ActionContext(null, userId, name, customId, new EmptyData());
        Action action = ActionManager.get(customId.getKey());

        ActionResult result = action.execute(context);

        Responder responder = new CommandResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
