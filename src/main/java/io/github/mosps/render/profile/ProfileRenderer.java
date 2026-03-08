package io.github.mosps.render.profile;

import io.github.mosps.data.Classes;
import io.github.mosps.data.Imagines;
import io.github.mosps.render.BaseRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.profile.ProfileView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.selections.SelectOption;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.util.Arrays;
import java.util.List;


public class ProfileRenderer extends BaseRenderer<ProfileView> {

    @Override
    public RenderResult render(ProfileView view) {
        EmbedBuilder embedBuilder = baseEmbed();

        embedBuilder.setTitle("プロフィール");

        embedBuilder.setDescription(
                """
                %s
                -----------------------------------
                **メインクラス:**
                __%s__
                **サブクラス:**
                %s
                **所持イマジン:**
                %s
                -----------------------------------
                """

        .formatted(view.name, view.mainClass, view.subClasses, view.imagines));

        StringSelectMenu mainClass = StringSelectMenu.create("profile:register:main_class")
                .setPlaceholder("メインクラスを選択")
                .addOptions(Arrays.stream(Classes.values())
                        .map(c -> SelectOption.of(c.getName(), c.name())
                                .withDescription(c.getStyle())
                                .withEmoji(Emoji.fromFormatted(c.getEmoji())))
                        .toList())
                .build();
        StringSelectMenu subClass = StringSelectMenu.create("profile:register:sub_class")
                .setPlaceholder("サブクラスを選択")
                .addOptions(Arrays.stream(Classes.values())
                        .map(c -> SelectOption.of(c.getName(), c.name())
                                .withDescription(c.getStyle())
                                .withEmoji(Emoji.fromFormatted(c.getEmoji())))
                        .toList())
                .setMinValues(0)
                .setMaxValues(5)
                .build();
        StringSelectMenu imagine = StringSelectMenu.create("profile:register:imagine")
                .setPlaceholder("バトルイマジンを選択")
                .addOptions(Arrays.stream(Imagines.values())
                        .map(v -> SelectOption.of(v.getName(), v.name())
                                .withEmoji(Emoji.fromFormatted(v.getEmoji())))
                        .toList())
                .build();

        List<ActionRow> rows = List.of(
                ActionRow.of(mainClass),
                ActionRow.of(subClass),
                ActionRow.of(imagine)
        );

        return build(MessageEditData.fromEmbeds(embedBuilder.build()), rows);
    }
}
