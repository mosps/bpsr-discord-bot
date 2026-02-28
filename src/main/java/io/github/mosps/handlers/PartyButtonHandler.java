package io.github.mosps.handlers;

import io.github.mosps.render.MessageRenderer;
import io.github.mosps.session.SessionData;
import io.github.mosps.session.SessionManager;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class PartyButtonHandler implements ButtonHandler {

    public void handle(ButtonInteractionEvent event, String[] customId) {
        String action = customId[1];
        String sessionId = customId[2];

        SessionData session = SessionManager.getSession(sessionId);

        if (session == null) {
            event.reply("期限切れの募集です。").setEphemeral(true).queue();
            return;
        }
        if (session.isClosed()) {
            event.reply("締め切られた募集です。").setEphemeral(true).queue();
            return;
        }

        long userId = event.getUser().getIdLong();

        switch (action) {
            case "join" -> session.addMembers(userId);
            case "leave" -> session.removeMembers(userId);
            case "close"  -> {
                if (userId != session.getOwnerId()) {
                    event.reply("募集作成者ではありません").setEphemeral(true).queue();
                    return;
                }
                session.close();
            }
        }

        event.editMessageEmbeds(
                MessageRenderer.render(session)
        ).setComponents(
                MessageRenderer.buttons(session)
        ).queue();
    }
}
