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
        embedBuilder.setTitle("Party Info");

        return embedBuilder;
    }

    private void addMemberListField(PartyInfoView view, EmbedBuilder embedBuilder) {
        List<MemberView> memberViews = view.members;
        if (memberViews.isEmpty()) {
            embedBuilder.addField("パーティメンバーが居ません...", " ", false);
            return;
        }

        memberViews.forEach(memberView -> embedBuilder.addField(buildMemberString(memberView), buildValueField(memberView), false));
    }

    private String buildMemberString(MemberView view) {
        return """
               %s %s%s
               """.formatted(view.name, view.emoji, view.style);
    }

    private String buildValueField(MemberView view) {
        String imagine = buildImagineString(view);
        if (imagine.length() > 1024) {
            imagine = imagine.substring(0, 980);

            int lastStart = imagine.lastIndexOf("<");
            int lastEnd = imagine.lastIndexOf(">");

            if (lastStart > lastEnd) {
                imagine = imagine.substring(0, lastStart);
            }

            imagine = imagine + "...";
        }

        return """
               (<@%s>)
               %s
               """.formatted(view.userId, imagine);
    }

    private String buildImagineString(MemberView view) {
        return view.imagines.isEmpty() ? "バトルイマジン未設定" : view.imagines;
    }
}
