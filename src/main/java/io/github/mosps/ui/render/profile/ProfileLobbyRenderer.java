package io.github.mosps.ui.render.profile;

import io.github.mosps.ui.render.BaseRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.profile.ProfileLobbyView;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

public class ProfileLobbyRenderer extends BaseRenderer<ProfileLobbyView> {

    @Override
    public RenderResult render(ProfileLobbyView view) {
        String context = "ボタンを押してプロフィールを表示します。";

        Button create = Button.success("profile:create", "🧾プロフィールを表示");

        return build(MessageEditData.fromContent(context), ActionRow.of(create));
    }
}
