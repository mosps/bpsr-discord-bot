package io.github.mosps.render.profile;

import io.github.mosps.data.Imagines;
import io.github.mosps.render.BaseRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.profile.imagine.ImagineEditView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.selections.SelectOption;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.util.Arrays;
import java.util.List;

public class ProfileImagineEditRenderer extends BaseRenderer<ImagineEditView> {

    @Override
    public RenderResult render(ImagineEditView view) {
        EmbedBuilder embedBuilder = baseEmbed();

        embedBuilder.setTitle("イマジン編集");

        embedBuilder.setDescription(
                """
                登録するイマジンを選択(複数選択可)後
                凸数を指定してください。
                
                登録済みのイマジンを再度登録することで
                削除することもできます。
                -----------------------------------
                **選択中**
                登録 》__%s__
                削除 》__%s__
                **凸数指定**
                %s
                -----------------------------------
                """.formatted(view.entry, view.delete, view.tier));

        StringSelectMenu imagineEntry = StringSelectMenu.create("profile:imagine:add:" + view.userId)
                .setPlaceholder("登録するバトルイマジンを選択")
                .addOptions(Arrays.stream(Imagines.values())
                        .filter(v -> !view.imagines.contains(v))
                        .map(v -> SelectOption.of(v.getName(), v.name())
                                .withEmoji(Emoji.fromFormatted(v.getEmoji())))
                        .toList())
                .setMaxValues(25)
                .build();
        StringSelectMenu imagineRemove = StringSelectMenu.create("profile:imagine:remove:" + view.userId)
                .setPlaceholder("削除するバトルイマジンを選択")
                .addOptions(view.imagines.stream()
                        .map(v -> SelectOption.of(v.getName(), v.name())
                                .withEmoji(Emoji.fromFormatted(v.getEmoji())))
                        .toList())
                .setMaxValues(25)
                .build();
        StringSelectMenu.Builder numberBuilder = StringSelectMenu.create("profile:imagine:tier:" + view.userId)
                .setPlaceholder("凸数を選択");

        for (int i = 0; i <= 6; i++) {
            numberBuilder.addOption(i + "凸", i + "凸");
        }
        StringSelectMenu number = numberBuilder.build();

        Button success = Button.success("profile:success:" + view.userId, "✅");
        Button previous = Button.secondary("profile:previous:" + view.userId, "⬅️");
        Button next = Button.secondary("profile:next:" + view.userId, "➡️️");

        List<ActionRow> rows = List.of(
                ActionRow.of(imagineEntry),
                ActionRow.of(imagineRemove),
                ActionRow.of(number),
                ActionRow.of(previous, success,  next)
        );

        return build(MessageEditData.fromEmbeds(embedBuilder.build()), rows);
    }
}
