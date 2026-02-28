package io.github.mosps.render;

import io.github.mosps.session.SessionData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.util.ArrayList;
import java.util.List;

public class MessageRenderer {

    public static MessageEmbed render(SessionData session) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("パーティ募集");

        StringBuilder sb = new StringBuilder();
        session.getMembers().forEach(member ->
            sb.append("⊳").append(member).append('\n')
        );

        eb.addField("参加者",
                sb.isEmpty() ? " " : sb.toString(),
                false
        );

        eb.setFooter(
                session.isClosed() ? "募集終了" : "参加受付中"
        );

        return eb.build();
    }

    public static List<ActionRow> buttons(SessionData session) {
        if (session.isClosed()) {
            return List.of();
        }

        return List.of(
                ActionRow.of(
                        Button.success("party_join", "参加"),
                        Button.danger("party_leave", "退出"),
                        Button.secondary("party_close", "終了")
                )
        );
    }
}
