package io.github.mosps.handlers.actions.party.setting;

import io.github.mosps.handlers.actions.Action;
import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.handlers.actions.ActionResult;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import io.github.mosps.render.RenderResult;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.util.List;

public class PartySettingAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.createParty(context.getUserId());

        RenderResult render = new RenderResult(
                MessageEditData.fromContent("パーティ設定"),
                List.of(createSettingButtonRow(party.getPartyId()), createRoleRow(party.getPartyId()))
        );

        return ActionResult.of()
                .withEphemeral(render);
    }

    public ActionRow createRoleRow(String partyId) {
        StringSelectMenu role = StringSelectMenu.create("party:role:" + partyId)
                .setPlaceholder("パーティのロール構成を選択")
                .addOption("ダンジョン 5人パーティ", "NORMAL_5")
                .addOption("フリー 5人パーティ", "FREE_5")
                .addOption("レイド 20人パーティ", "NORMAL_20")
                .addOption("フリー 20人パーティ", "FREE_20")
                .build();

        return ActionRow.of(role);
    }

    public ActionRow createSettingButtonRow(String partyId) {
        Button close = Button.secondary("party:close:" + partyId, "終了");
        Button edit = Button.secondary("party:edit:" + partyId, "編集");

        return ActionRow.of(close, edit);
    }
}
