package io.github.mosps.handlers;

import io.github.mosps.render.MessageRenderer;
import io.github.mosps.session.SessionData;
import io.github.mosps.session.SessionManager;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class PartyButtonHandler implements ButtonHandler {

    public boolean handle(ButtonInteractionEvent event) {
        if (!event.getComponentId().startsWith("party")) return false;

        long messageId = event.getMessageIdLong();
        SessionData session = SessionManager.getSession(messageId);

        if (session == null) {
            event.reply("期限切れの募集です。").setEphemeral(true).queue();
            return true;
        }
        if (session.isClosed()) {
            event.reply("締め切られた募集です。").setEphemeral(true).queue();
            return true;
        }

        long userId = event.getUser().getIdLong();

        switch (event.getComponentId()) {
            case "party_join":
                session.addMembers(userId);
                break;
            case "party_leave":
                session.removeMembers(userId);
                break;
            case "party_close":
                if (userId != session.getOwnerId()) {
                    event.reply("募集作成者ではありません").setEphemeral(true).queue();
                    return true;
                }
                session.close();
                break;
        }

        event.editMessageEmbeds(
                MessageRenderer.render(session)
        ).setComponents(
                MessageRenderer.buttons(session)
        ).queue();

        return true;
    }
}
