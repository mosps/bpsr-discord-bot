package io.github.mosps.ui.render.profile.imagine;

import io.github.mosps.model.data.Imagines;
import io.github.mosps.ui.render.BaseRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.render.util.PageManager;
import io.github.mosps.ui.views.profile.imagine.ImagineEditView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.selections.SelectOption;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImagineEditRenderer extends BaseRenderer<ImagineEditView> {

    @Override
    public RenderResult render(ImagineEditView view) {
        EmbedBuilder embedBuilder = buildEmbed(view);

        List<ActionRow> rows = new ArrayList<>();
        rows.add(createTierRow(view));
        createEntryRow(view).ifPresent(rows::add);
        createAddImaginePageButtonRow(view).ifPresent(rows::add);
        createRemoveRow(view).ifPresent(rows::add);
        rows.add(createLastButtonRow(view));

        return build(MessageEditData.fromEmbeds(embedBuilder.build()), rows);
    }

    private EmbedBuilder buildEmbed(ImagineEditView view) {
        EmbedBuilder embedBuilder = baseEmbed();

        embedBuilder.setColor(Color.ORANGE);

        embedBuilder.setTitle("イマジン編集");

        embedBuilder.setDescription(
                """
                凸数を指定後、登録するイマジンを選択してください
                登録済みイマジンの削除もできます。
                
                確定ボタンで登録・削除を保存します。
                
                ※この設定画面は10分後に期限切れとなります。
                -----------------------------------
                **凸数指定**
                %s
                **登録**
                %s
                **削除**
                %s
                -----------------------------------
                """.formatted(view.tier, view.add, view.remove));

        return embedBuilder;
    }

    private Optional<ActionRow> createEntryRow(ImagineEditView view) {
        if (view.availableImagines.isEmpty()) return Optional.empty();

        StringSelectMenu imagineEntry = StringSelectMenu.create("profile:imagine_edit:add|" + view.userId)
                .setPlaceholder("登録するバトルイマジンを選択 (" + (view.addImaginePage + 1) + "/" + PageManager.totalPage(view.availableImagines.size()) + ")")
                .addOptions(buildAddImagineOptions(view))
                .setMaxValues(25)
                .build();

        return Optional.of(ActionRow.of(imagineEntry));
    }

    private Optional<ActionRow> createRemoveRow(ImagineEditView view) {
        if (view.currentImagines.isEmpty()) return Optional.empty();

        StringSelectMenu imagineRemove = StringSelectMenu.create("profile:imagine_edit:remove|" + view.userId)
                .setPlaceholder("削除するバトルイマジンを選択 (" + (view.removeImaginePage + 1) + "/" + PageManager.totalPage(view.currentImagines.size()) + ")")
                .addOptions(buildRemoveImagineOptions(view))
                .setMaxValues(25)
                .build();

        return Optional.of(ActionRow.of(imagineRemove));
    }

    private ActionRow createTierRow(ImagineEditView view) {
        StringSelectMenu.Builder numberBuilder = StringSelectMenu.create("profile:imagine_edit:tier|" + view.userId)
                .setPlaceholder("凸数を選択");

        for (int i = 0; i <= 4; i++) {
            numberBuilder.addOption(i + "凸", i + "凸");
        }
        numberBuilder.addOption("__5凸__", "__5凸__");

        return ActionRow.of(numberBuilder.build());
    }

    private Optional<ActionRow> createAddImaginePageButtonRow(ImagineEditView view) {
        if (view.availableImagines.isEmpty()) return Optional.empty();

        Button previous = Button.secondary("profile:imagine_add_prev:|" + view.userId, "⬅️前のページ");
        Button next = Button.secondary("profile:imagine_add_next:|" + view.userId, "次のページ➡️️");

        if (!PageManager.hasNext(view.addImaginePage, view.availableImagines.size())) {
            next = next.asDisabled();
        }
        if (!PageManager.hasPrev(view.addImaginePage)) {
            previous = previous.asDisabled();
        }

        return Optional.of(ActionRow.of(previous, next));
    }

    private List<Button> createRemoveImaginePageButtonRow(ImagineEditView view) {
        if (view.currentImagines.isEmpty()) return List.of();

        Button previous = Button.secondary("profile:imagine_remove_prev:|" + view.userId, "⬅️前のページ");
        Button next = Button.secondary("profile:imagine_remove_next:|" + view.userId, "次のページ➡️️");

        if (!PageManager.hasNext(view.removeImaginePage, view.currentImagines.size())) {
            next = next.asDisabled();
        }
        if (!PageManager.hasPrev(view.removeImaginePage)) {
            previous = previous.asDisabled();
        }

        return List.of(previous, next);
    }

    private List<Button> createConfirmButtonRow(ImagineEditView view) {
        Button success = Button.success("profile:imagine_confirm:|" + view.userId, "✅確定");

        return List.of(success);
    }

    private ActionRow createLastButtonRow(ImagineEditView view) {
        List<Button> buttons = new ArrayList<>();

        buttons.addAll(createRemoveImaginePageButtonRow(view));
        buttons.addAll(createConfirmButtonRow(view));

        return ActionRow.of(buttons);
    }

    public List<SelectOption> buildAddImagineOptions(ImagineEditView view) {
        List<Imagines> current = PageManager.getPage(view.availableImagines, view.addImaginePage);

        return current.stream()
                .map(v -> SelectOption.of(v.getName(), v.name() + ":" + view.tier)
                        .withEmoji(Emoji.fromFormatted(v.getEmoji())))
                .toList();
    }

    public List<SelectOption> buildRemoveImagineOptions(ImagineEditView view) {
        List<Imagines> current = PageManager.getPage(new ArrayList<>(view.currentImagines.keySet()), view.removeImaginePage);

        return current.stream()
                .map(v -> SelectOption.of(v.getName(), v.name() + ":" + view.tier)
                        .withEmoji(Emoji.fromFormatted(v.getEmoji())))
                .toList();
    }
}
