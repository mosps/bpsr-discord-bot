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

        Modal modal = Modal.create("party:edit_confirm:" + context.getCustomId().get("partyId"), "パーティ設定").
                addComponents(
                        Label.of("目的地", destination),
                        Label.of("開始時刻", time),
                        Label.of("備考", note)
                ).build();

        return ActionResult.of().withModal(modal);
    }
}
