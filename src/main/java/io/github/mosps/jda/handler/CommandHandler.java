package io.github.mosps.jda.handler;

import io.github.mosps.actions.*;
import io.github.mosps.actions.data.EmptyData;
import io.github.mosps.jda.response.CommandResponder;
import io.github.mosps.jda.response.Responder;
import io.github.mosps.jda.response.ResponseDispatcher;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandHandler extends Handler {

    public static void handle(SlashCommandInteractionEvent event) {
        CustomId customId = CustomId.of(event.getName() + ":" + event.getSubcommandName());
        long guildId = requireGuildId(event);
        long userId = event.getUser().getIdLong();
        String name = event.getMember() == null
                ? event.getUser().getEffectiveName()
                : event.getMember().getEffectiveName();

        ActionContext context = new ActionContext(guildId, null, userId, name, customId, new EmptyData());

        ActionResult result = ActionExecutor.execute(context);

        Responder responder = new CommandResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
