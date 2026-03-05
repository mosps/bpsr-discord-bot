package io.github.mosps.render.party;

import io.github.mosps.render.BaseRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

public class PartyRenderer extends BaseRenderer<PartyView> {

    @Override
    public RenderResult render(PartyView view) {
        EmbedBuilder embedBuilder = baseEmbed();

        StringBuilder stringBuilder = new StringBuilder();
        view.members.forEach(member ->
                stringBuilder.append("<@").append(member).append(">\n")
        );

        embedBuilder.setTitle("パーティ募集");

        embedBuilder.setDescription(
                        "-----------------------------------\n" +
                        "目的地: カナミア\n" +
                        "-----------------------------------"
                        );

        embedBuilder.addField("参加者 " + "```" + view.members.size() + "/" + view.maxMembers + "```",
                stringBuilder.isEmpty() ? "　" : stringBuilder.toString(),
                false
        );

        Button join = Button.success("party:join:" + view.partyId, "参加");
        Button leave = Button.danger("party:leave:" + view.partyId, "退出");
        Button close = Button.secondary("party:close:" + view.partyId, "終了");

        if (view.closed) {
            join = join.asDisabled();
            leave = leave.asDisabled();
            close = close.asDisabled();
        }

        return build(MessageEditData.fromEmbeds(embedBuilder.build()), ActionRow.of(join, leave, close));
    }
}
