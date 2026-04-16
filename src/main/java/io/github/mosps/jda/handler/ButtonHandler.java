package io.github.mosps.jda.handler;

import io.github.mosps.actions.*;
import io.github.mosps.actions.data.EmptyData;
import io.github.mosps.jda.response.ButtonResponder;
import io.github.mosps.jda.response.Responder;
import io.github.mosps.jda.response.ResponseDispatcher;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.util.Objects;

public class ButtonHandler extends Handler {

    public static void handle(ButtonInteractionEvent event) {
        CustomId customId = CustomId.of(event.getComponentId());
        long guildId = requireGuildId(event);
        String messageId = event.getMessageId();
        long userId = event.getUser().getIdLong();
        String name = event.getMember() == null
                ? event.getUser().getEffectiveName()
                : event.getMember().getEffectiveName();

        ActionContext context = new ActionContext(guildId, messageId, userId, name, customId, new EmptyData());

        ActionResult result = ActionExecutor.execute(context);

        Responder responder = new ButtonResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
