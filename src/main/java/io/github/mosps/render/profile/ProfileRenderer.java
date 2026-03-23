package io.github.mosps.render.profile;

import io.github.mosps.data.Classes;
import io.github.mosps.render.BaseRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.profile.ProfileView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.selections.SelectOption;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProfileRenderer extends BaseRenderer<ProfileView> {

    @Override
    public RenderResult render(ProfileView view) {
        EmbedBuilder embedBuilder = buildEmbed(view);

        List<ActionRow> rows = new ArrayList<>();
        rows.add(createMainClassRow(view));
        rows.add(createSubClassRow(view));
        rows.add(createEditClassButtonRow(view));

        return build(MessageEditData.fromEmbeds(embedBuilder.build()), rows);
    }

    private EmbedBuilder buildEmbed(ProfileView view) {
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
                """.formatted(view.name, view.mainClass, view.subClasses, view.imagines));

        return embedBuilder;
    }

    private ActionRow createMainClassRow(ProfileView view) {
        StringSelectMenu mainClass = StringSelectMenu.create("profile:register:main_class:" + view.userId)
                .setPlaceholder("メインクラスを選択")
                .addOptions(Arrays.stream(Classes.values())
                        .map(c -> SelectOption.of(c.getName(), c.name())
                                .withDescription(c.getStyle())
                                .withEmoji(Emoji.fromFormatted(c.getEmoji())))
                        .toList())
                .build();

        return ActionRow.of(mainClass);
    }

    private ActionRow createSubClassRow(ProfileView view) {
        StringSelectMenu subClass = StringSelectMenu.create("profile:register:sub_class:" + view.userId)
                .setPlaceholder("サブクラスを選択")
                .addOptions(Arrays.stream(Classes.values())
                        .map(c -> SelectOption.of(c.getName(), c.name())
                                .withDescription(c.getStyle())
                                .withEmoji(Emoji.fromFormatted(c.getEmoji())))
                        .toList())
                .setMinValues(0)
                .setMaxValues(5)
                .build();

        return ActionRow.of(subClass);
    }

    private ActionRow createEditClassButtonRow(ProfileView view) {
        Button edit = Button.secondary("profile:imagine_edit:" + view.userId, "イマジンを変更");

        return ActionRow.of(edit);
    }
}
