package io.github.mosps.handlers.handler;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionManager;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.handlers.actions.data.ModalData;
import io.github.mosps.handlers.response.ModalResponder;
import io.github.mosps.handlers.response.Responder;
import io.github.mosps.handlers.response.ResponseDispatcher;
import io.github.mosps.util.customid.CustomId;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;

import java.util.Map;
import java.util.stream.Collectors;

public class ModalHandler {

    public static void handle(ModalInteractionEvent event) {
        CustomId customId = CustomId.of(event.getModalId());
        long userId = event.getUser().getIdLong();
        String name = event.getUser().getEffectiveName();

        Map<String,String> values = event.getValues().stream()
                .collect(Collectors.toMap(
                        ModalMapping::getCustomId,
                        ModalMapping::getAsString
                ));

        ActionContext context = new ActionContext(null, userId, name, customId, new ModalData(values));
        Action action = ActionManager.get(customId.getKey());

        ActionResult result = action.execute(context);

        Responder responder = new ModalResponder(event);
        ResponseDispatcher.dispatch(responder, result);
    }
}
