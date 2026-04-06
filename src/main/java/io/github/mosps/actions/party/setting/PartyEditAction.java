package io.github.mosps.actions.party.setting;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.model.party.Party;
import io.github.mosps.model.party.PartyManager;
import net.dv8tion.jda.api.components.label.Label;
import net.dv8tion.jda.api.components.textinput.TextInput;
import net.dv8tion.jda.api.components.textinput.TextInputStyle;
import net.dv8tion.jda.api.modals.Modal;

public class PartyEditAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.getParty(context.getCustomId().get("partyId"));
        if (party == null) {
            return ActionResult.of()
                    .error("このパーティは期限切れです。");
        }

        if (party.isClosed()) {
            return ActionResult.of()
                    .error("このパーティは締め切り済みです。");
        }

        if (context.getUserId() != party.getOwnerId()) {
            return ActionResult.of()
                    .error("パーティ作成者ではありません。");
        }

        TextInput.Builder destination = createTextInput(TextInputStyle.SHORT, "destination", "目的地", true);
        TextInput.Builder time = createTextInput(TextInputStyle.SHORT, "time", "開始時刻", true);
        TextInput.Builder note = createTextInput(TextInputStyle.PARAGRAPH, "note", "備考", false);

        setIfPresent(destination, party.getDestination());
        setIfPresent(time, party.getTime());
        setIfPresent(note, party.getNote());

        Modal modal = Modal.create("party:edit_confirm:" + context.getCustomId().get("partyId"), "パーティ設定").
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

    private void setIfPresent(TextInput.Builder builder, String value) {
        if (!value.isBlank()) {
            builder.setValue(value);
        }
    }
}
