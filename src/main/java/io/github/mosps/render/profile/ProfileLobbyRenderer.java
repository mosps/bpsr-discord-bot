package io.github.mosps.render.profile;

import io.github.mosps.render.BaseRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.profile.ProfileLobbyView;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

public class ProfileLobbyRenderer extends BaseRenderer<ProfileLobbyView> {

    @Override
    public RenderResult render(ProfileLobbyView view) {
        String context = "ボタンを押してプロフィールを登録します。";

        Button create = Button.success("profile:register", "プロフィールを登録");

        return build(MessageEditData.fromContent(context), ActionRow.of(create));
    }
}
