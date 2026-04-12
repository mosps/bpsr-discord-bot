package io.github.mosps.ui.render.profile;

import io.github.mosps.model.data.Classes;
import io.github.mosps.model.data.Imagines;
import io.github.mosps.ui.render.BaseRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.render.util.PageManager;
import io.github.mosps.ui.views.profile.ProfileView;
import io.github.mosps.ui.views.profile.imagine.ImagineEditView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.selections.SelectOption;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.awt.*;
import java.util.*;
import java.util.List;


public class ProfileRenderer extends BaseRenderer<ProfileView> {

    @Override
    public RenderResult render(ProfileView view) {
        EmbedBuilder embedBuilder = buildEmbed(view);

        List<ActionRow> rows = new ArrayList<>();
        rows.add(createMainClassRow(view));
        rows.add(createSubClassRow(view));
        createEquippedImaginesRow(view).ifPresent(rows::add);
        createPageButtonRow(view).ifPresent(rows::add);
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

        StringSelectMenu imagines = StringSelectMenu.create("profile:register:equipped_imagines|" + view.userId)
                .setPlaceholder("装備するイマジンを選択 (" + (view.page + 1) + "/" + PageManager.totalPage(view.ownedImagines.size()) + ")")
                .addOptions(buildImagineOptions(view))
                .setMinValues(0)
                .setMaxValues(2)
                .build();

        return Optional.of(ActionRow.of(imagines));
    }

    private Optional<ActionRow> createPageButtonRow(ProfileView view) {
        if (view.ownedImagines.isEmpty()) return Optional.empty();

        Button previous = Button.secondary("profile:prev:|" + view.userId, "⬅️前のページ");
        Button next = Button.secondary("profile:next:|" + view.userId, "次のページ➡️️");

        if (!PageManager.hasNext(view.page, view.ownedImagines.size())) {
            next = next.asDisabled();
        }
        if (!PageManager.hasPrev(view.page)) {
            previous = previous.asDisabled();
        }

        return Optional.of(ActionRow.of(previous, next));
    }

    private ActionRow createEditClassButtonRow(ProfileView view) {
        Button edit = Button.secondary("profile:imagine_create:|" + view.userId, "イマジンを変更");

        return ActionRow.of(edit);
    }

    public List<SelectOption> buildImagineOptions(ProfileView view) {
        List<Map.Entry<Imagines, String>> current = PageManager.getPage(new ArrayList<>(view.ownedImagines.entrySet()), view.page);

        return current.stream()
                .map(entry -> SelectOption.of(entry.getKey().getName(), entry.getKey().name() + ":" + entry.getValue())
                        .withEmoji(Emoji.fromFormatted(entry.getKey().getEmoji())))
                .toList();
    }
}
