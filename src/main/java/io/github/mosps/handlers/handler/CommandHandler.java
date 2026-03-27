package io.github.mosps.handlers.handler;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionManager;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.handlers.response.CommandResponder;
import io.github.mosps.handlers.response.Responder;
import io.github.mosps.handlers.response.ResponseDispatcher;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;

public class CommandHandler {

    public static void handle(SlashCommandInteractionEvent event) {
        CustomId customId = CustomId.of(event.getName() + ":" + event.getSubcommandName());
        long userId = event.getUser().getIdLong();
        String name = event.getUser().getEffectiveName();

        ActionContext context = new ActionContext(userId, name, customId, List.of());
        Action action = ActionManager.get(customId.getKey());

        ActionResult result = action.execute(context);

        Responder responder = new CommandResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
