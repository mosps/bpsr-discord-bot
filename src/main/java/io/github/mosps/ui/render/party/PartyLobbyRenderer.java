package io.github.mosps.ui.render.party;

import io.github.mosps.ui.render.BaseRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.party.PartyLobbyView;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

public class PartyLobbyRenderer extends BaseRenderer<PartyLobbyView> {

    @Override
    public RenderResult render(PartyLobbyView view) {
        String context = "ボタンを押してパーティを作成します。";

        Button create = Button.success("party:create", "📝パーティを作成");

        return build(MessageEditData.fromContent(context), ActionRow.of(create));
    }
}
