package io.github.mosps.render.party.setting;

import io.github.mosps.handlers.actions.ActionContext;
import io.github.mosps.render.BaseRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.setting.PartySettingView;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.util.ArrayList;
import java.util.List;

public class PartySettingRenderer extends BaseRenderer<PartySettingView> {

    @Override
    public RenderResult render(PartySettingView view) {
        String content = "パーティ設定";

        List<ActionRow> rows = new ArrayList<>();
        rows.add(createRoleRow(view));
        rows.add(createSettingButtonRow(view));

        return build(MessageEditData.fromContent(content), rows);
    }

    private ActionRow createRoleRow(PartySettingView view) {
        StringSelectMenu role = StringSelectMenu.create("party:role:" + view.partyId)
                .setPlaceholder("パーティのロール構成を選択")
                .addOption("ダンジョン 5人パーティ", "NORMAL_5")
                .addOption("フリー 5人パーティ", "FREE_5")
                .addOption("レイド 20人パーティ", "NORMAL_20")
                .addOption("フリー 20人パーティ", "FREE_20")
                .build();

        return ActionRow.of(role);
    }

    private ActionRow createSettingButtonRow(PartySettingView view) {
        Button edit = Button.secondary("party:edit:" + view.partyId, "編集");
        Button close = Button.secondary("party:toggle:" + view.partyId, "募集終了 | 募集再開");
        Button delete = Button.danger("party:delete:" + view.partyId, "削除");

        return ActionRow.of(edit, close, delete);
    }
}
