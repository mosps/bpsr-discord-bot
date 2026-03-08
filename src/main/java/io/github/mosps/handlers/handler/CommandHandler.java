package io.github.mosps.handlers.handler;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionManager;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.handlers.response.CommandResponder;
import io.github.mosps.handlers.response.Responder;
import io.github.mosps.handlers.response.ResponseDispatcher;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandHandler {

    public static void handle(SlashCommandInteractionEvent event) {
        String key = event.getName() + ":" + event.getSubcommandName();

        ActionContext context = new ActionContext(event.getUser().getIdLong(), event.getUser().getEffectiveName());
        Action action = ActionManager.get(key);

        ActionResult result = action.execute(context);

        Responder responder = new CommandResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
