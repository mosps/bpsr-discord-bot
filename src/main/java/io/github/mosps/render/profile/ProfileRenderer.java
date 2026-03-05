package io.github.mosps.render.profile;

import io.github.mosps.render.BaseRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;
import io.github.mosps.views.profile.ProfileLobbyView;
import io.github.mosps.views.profile.ProfileView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

public class ProfileRenderer extends BaseRenderer<ProfileView> {

    @Override
    public RenderResult render(ProfileView view) {
        return build(MessageEditData.fromContent("test"), ActionRow.of(Button.secondary("test", "test")));
    }
}
