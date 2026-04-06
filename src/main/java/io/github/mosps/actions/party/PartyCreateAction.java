package io.github.mosps.actions.party;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import net.dv8tion.jda.api.components.label.Label;
import net.dv8tion.jda.api.components.textinput.TextInput;
import net.dv8tion.jda.api.components.textinput.TextInputStyle;
import net.dv8tion.jda.api.modals.Modal;

public class PartyCreateAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        TextInput.Builder destination = createTextInput(TextInputStyle.SHORT, "destination", "目的地", true);
        TextInput.Builder time = createTextInput(TextInputStyle.SHORT, "time", "開始時刻", true);
        TextInput.Builder note = createTextInput(TextInputStyle.PARAGRAPH, "note", "備考", false);

        Modal modal = Modal.create("party:create_confirm:" + context.getUserId(), "パーティ設定").
                addComponents(
                        Label.of("目的地", destination.build()),
                        Label.of("開始時刻", time.build()),
                        Label.of("備考", note.build())
                ).build();

        return ActionResult.of().withModal(modal);
    }

    private TextInput.Builder createTextInput(TextInputStyle style, String id, String text, boolean required) {
        return TextInput.create(id, style)
                .setPlaceholder(text)
                .setRequired(required);
    }
}
