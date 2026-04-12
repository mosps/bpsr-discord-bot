package io.github.mosps.ui.render.party;

import io.github.mosps.ui.render.BaseRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.party.PartyInfoView;
import io.github.mosps.ui.views.profile.MemberView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.awt.*;
import java.util.List;

public class PartyInfoRenderer extends BaseRenderer<PartyInfoView> {

    @Override
    public RenderResult render(PartyInfoView view) {
        EmbedBuilder embedBuilder = buildEmbed(view);
        addMemberListField(view, embedBuilder);

        return build(MessageEditData.fromEmbeds(embedBuilder.build()), List.of());
    }

    private EmbedBuilder buildEmbed(PartyInfoView view) {
        EmbedBuilder embedBuilder = baseEmbed();

        embedBuilder.setColor(Color.CYAN);

        return embedBuilder;
    }

    private void addMemberListField(PartyInfoView view, EmbedBuilder embedBuilder) {
        List<String> members = buildMembersString(view);
        if (members.isEmpty()) {
            embedBuilder.addField("Party Info", " ", false);
            return;
        }

        int total = members.size();
        for (int i = 0; i < total; i += 5) {
            List<String> chunk = members.subList(i, Math.min(i + 5, total));

            String value = String.join("\n", chunk);

            if (value.length() > 1024) {
                value = value.substring(0, 1020) + "...";
            }

            String name = (i == 0)
                    ? "Party Info"
                    : "\u200B";

            embedBuilder.addField(name, value, false);
        }
    }

    private List<String> buildMembersString(PartyInfoView view) {
        return view.members.stream()
                .map(memberView ->
                            "- "+ memberView.name + " "
                            + memberView.emoji + memberView.style + " "
                            + "(<@" + memberView.userId + ">)"
                            + buildImagineString(memberView)
                ).toList();
    }

    private String buildImagineString(MemberView view) {
        return view.imagines.isEmpty() ? "" : "\n" + view.imagines;
    }
}
