package io.github.mosps.ui.render.profile.imagine;

import io.github.mosps.model.data.Imagines;
import io.github.mosps.model.party.PartyManager;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ImagineEditRenderer extends BaseRenderer<ImagineEditView> {

    @Override
    public RenderResult render(ImagineEditView view) {
        EmbedBuilder embedBuilder = buildEmbed(view);

        List<ActionRow> rows = new ArrayList<>();
        rows.add(createTierRow(view));
        rows.add(createEntryRow(view));
        rows.add(createPageButtonRow(view));
        createRemoveRow(view).ifPresent(rows::add);
        rows.add(createConfirmButtonRow(view));

        return build(MessageEditData.fromEmbeds(embedBuilder.build()), rows);
    }

    private EmbedBuilder buildEmbed(ImagineEditView view) {
        EmbedBuilder embedBuilder = baseEmbed();

        embedBuilder.setColor(Color.ORANGE);

        embedBuilder.setTitle("イマジン編集");

        embedBuilder.setDescription(
                """
                登録するイマジンを選択(複数選択可)後
                凸数を指定してください。
                
                登録済みのイマジンを再度登録することで
                削除することもできます。
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

    private ActionRow createEntryRow(ImagineEditView view) {
        StringSelectMenu imagineEntry = StringSelectMenu.create("profile:imagine_edit:add|" + view.userId)
                .setPlaceholder("登録するバトルイマジンを選択 (" + (view.page + 1) + "/" + PageManager.totalPage(view.availableImagines.size()) + ")")
                .addOptions(buildImagineOptions(view))
                .setMaxValues(25)
                .build();

        return ActionRow.of(imagineEntry);
    }

    private Optional<ActionRow> createRemoveRow(ImagineEditView view) {
        if (view.currentImagines.isEmpty()) return Optional.empty();

        StringSelectMenu imagineRemove = StringSelectMenu.create("profile:imagine_edit:remove|" + view.userId)
                .setPlaceholder("削除するバトルイマジンを選択")
                .addOptions(view.currentImagines.entrySet().stream()
                        .map(entry -> SelectOption.of(entry.getKey().getName(), entry.getKey().name() + ":" + entry.getValue())
                                .withEmoji(Emoji.fromFormatted(entry.getKey().getEmoji())))
                        .toList())
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

    private ActionRow createPageButtonRow(ImagineEditView view) {
        Button previous = Button.secondary("profile:imagine_prev:|" + view.userId, "⬅️前のページ");
        Button next = Button.secondary("profile:imagine_next:|" + view.userId, "次のページ➡️️");

        if (!PageManager.hasNext(view.page, view.availableImagines.size())) {
            next = next.asDisabled();
        }
        if (!PageManager.hasPrev(view.page)) {
            previous = previous.asDisabled();
        }

        return ActionRow.of(previous, next);
    }

    private ActionRow createConfirmButtonRow(ImagineEditView view) {
        Button success = Button.success("profile:imagine_confirm:|" + view.userId, "✅確定");

        return ActionRow.of(success);
    }

    public static List<SelectOption> buildImagineOptions(ImagineEditView view) {
        List<Imagines> current = PageManager.getPage(view.availableImagines, view.page);

        return current.stream()
                .map(v -> SelectOption.of(v.getName(), v.name() + ":" + view.tier)
                        .withEmoji(Emoji.fromFormatted(v.getEmoji())))
                .toList();
    }
}
