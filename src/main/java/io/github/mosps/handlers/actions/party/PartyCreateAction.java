package io.github.mosps.handlers.actions.party;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;
import net.dv8tion.jda.api.components.label.Label;
import net.dv8tion.jda.api.components.textinput.TextInput;
import net.dv8tion.jda.api.components.textinput.TextInputStyle;
import net.dv8tion.jda.api.modals.Modal;

public class PartyCreateAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        TextInput destination = TextInput.create("destination", TextInputStyle.SHORT)
                .setPlaceholder("目的地")
                .setRequired(true)
                .build();

        TextInput time = TextInput.create("time", TextInputStyle.SHORT)
                .setPlaceholder("開始時刻")
                .setRequired(true)
                .build();

        TextInput note = TextInput.create("note", TextInputStyle.PARAGRAPH)
                .setPlaceholder("備考")
                .setRequired(false)
                .build();

        Modal modal = Modal.create("party:create_confirm:" + context.getUserId(), "パーティ設定").
                addComponents(
                        Label.of("目的地", destination),
                        Label.of("開始時刻", time),
                        Label.of("備考", note)
                ).build();

        return ActionResult.of().withModal(modal)
                .withEphemeral("パーティを作成しました！");
    }
}
