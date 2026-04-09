package io.github.mosps.jda.handler;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionManager;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.actions.data.ModalData;
import io.github.mosps.jda.response.ModalResponder;
import io.github.mosps.jda.response.Responder;
import io.github.mosps.jda.response.ResponseDispatcher;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;

import java.util.Map;
import java.util.stream.Collectors;

public class ModalHandler extends Handler {

    public static void handle(ModalInteractionEvent event) {
        CustomId customId = CustomId.of(event.getModalId());
        long guildId = requireGuildId(event);
        long userId = event.getUser().getIdLong();
        String name = event.getMember() == null
                ? event.getUser().getEffectiveName()
                : event.getMember().getEffectiveName();

        Map<String,String> values = event.getValues().stream()
                .collect(Collectors.toMap(
                        ModalMapping::getCustomId,
                        ModalMapping::getAsString
                ));

        ActionContext context = new ActionContext(guildId, null, userId, name, customId, new ModalData(values));
        Action action = ActionManager.get(customId.getKey());

        ActionResult result = action.execute(context);

        Responder responder = new ModalResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
