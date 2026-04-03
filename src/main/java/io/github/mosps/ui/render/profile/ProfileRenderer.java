package io.github.mosps.ui.render.profile;

import io.github.mosps.model.data.Classes;
import io.github.mosps.ui.render.BaseRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.profile.ProfileView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.selections.SelectOption;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class ProfileRenderer extends BaseRenderer<ProfileView> {

    @Override
    public RenderResult render(ProfileView view) {
        EmbedBuilder embedBuilder = buildEmbed(view);

        List<ActionRow> rows = new ArrayList<>();
        rows.add(createMainClassRow(view));
        rows.add(createSubClassRow(view));
        createEquippedImaginesRow(view).ifPresent(rows::add);
        rows.add(createEditClassButtonRow(view));

        return build(MessageEditData.fromEmbeds(embedBuilder.build()), rows);
    }

    private EmbedBuilder buildEmbed(ProfileView view) {
        EmbedBuilder embedBuilder = baseEmbed();

        embedBuilder.setColor(Color.CYAN);

        embedBuilder.setTitle("プロフィール");

        embedBuilder.setDescription(
                """
                %s
                -----------------------------------
                **メインクラス:**
                __%s__
                **サブクラス:**
                %s
                **装備イマジン**
                %s
                **所持イマジン:**
                %s
                -----------------------------------
                """.formatted(view.name, view.mainClass, view.subClasses, view.equippedImagines, view.imagines));

        return embedBuilder;
    }

    private ActionRow createMainClassRow(ProfileView view) {
        StringSelectMenu mainClass = StringSelectMenu.create("profile:register:main_class|" + view.userId)
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
        StringSelectMenu subClass = StringSelectMenu.create("profile:register:sub_class|" + view.userId)
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

    private Optional<ActionRow> createEquippedImaginesRow(ProfileView view) {
        if (view.ownedImagines.isEmpty()) return Optional.empty();

        StringSelectMenu subClass = StringSelectMenu.create("profile:register:equipped_imagines|" + view.userId)
                .setPlaceholder("装備するイマジンを選択")
                .addOptions(view.ownedImagines.entrySet().stream()
                        .map(entry -> SelectOption.of(entry.getKey().getName(), entry.getKey().name() + ":" + entry.getValue())
                                .withEmoji(Emoji.fromFormatted(entry.getKey().getEmoji())))
                        .toList())
                .setMinValues(0)
                .setMaxValues(2)
                .build();

        return Optional.of(ActionRow.of(subClass));
    }

    private ActionRow createEditClassButtonRow(ProfileView view) {
        Button edit = Button.secondary("profile:imagine_edit:|" + view.userId, "イマジンを変更");

        return ActionRow.of(edit);
    }
}
