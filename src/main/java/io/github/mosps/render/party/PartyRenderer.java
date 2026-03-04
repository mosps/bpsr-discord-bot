package io.github.mosps.render.party;

import io.github.mosps.render.BaseRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

public class PartyRenderer extends BaseRenderer<PartyView> {

    @Override
    public RenderResult render(PartyView view) {
        EmbedBuilder embedBuilder = baseEmbed();

        StringBuilder stringBuilder = new StringBuilder();
        view.members.forEach(member ->
                stringBuilder.append("⊳").append(member).append('\n')
        );

        embedBuilder.addField("参加者",
                stringBuilder.isEmpty() ? " " : stringBuilder.toString(),
                false
        );

        embedBuilder.setFooter(
                view.closed ? "募集終了" : "参加受付中"
        );

        Button join = Button.success("party:join:" + view.partyId, "参加");
        Button leave = Button.danger("party:leave:" + view.partyId, "退出");
        Button close = Button.primary("party:close:" + view.partyId, "終了");

        return build(MessageEditData.fromEmbeds(embedBuilder.build()), ActionRow.of(join, leave, close));
    }
}
