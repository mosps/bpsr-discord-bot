package io.github.mosps.ui.render.party.setting;

import io.github.mosps.ui.render.BaseRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.party.setting.PartyDeleteView;
import io.github.mosps.ui.views.party.setting.PartySettingView;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.util.ArrayList;
import java.util.List;

public class PartyDeleteRenderer extends BaseRenderer<PartyDeleteView> {

    @Override
    public RenderResult render(PartyDeleteView view) {
        String content = "パーティを削除しますか？";

        List<ActionRow> rows = new ArrayList<>();
        rows.add(createCheckButtonRow(view));

        return build(MessageEditData.fromContent(content), rows);
    }

    private ActionRow createCheckButtonRow(PartyDeleteView view) {
        Button yes = Button.success("party:delete_accept:" + view.partyId, "はい");
        Button no = Button.danger("party:delete_deny:" + view.partyId, "いいえ");

        return ActionRow.of(yes, no);
    }
}
