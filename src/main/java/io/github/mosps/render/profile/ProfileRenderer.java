package io.github.mosps.render.profile;

import io.github.mosps.render.BaseRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.profile.ProfileView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.util.List;

public class ProfileRenderer extends BaseRenderer<ProfileView> {

    @Override
    public RenderResult render(ProfileView view) {

        EmbedBuilder embed = baseEmbed();

        embed.setTitle(view.name + " のプロフィール");

        embed.addField(
                "クラス",
                view.mainClass == null ? "未設定" : view.mainClass,
                true
        );

        embed.addField(
                "装備",
                view.imagines == null ? "未設定" : view.imagines.toString(),
                true
        );

        return build(MessageEditData.fromEmbeds(embed.build()), ActionRow.of(Button.secondary("test", "test")));
    }
}
