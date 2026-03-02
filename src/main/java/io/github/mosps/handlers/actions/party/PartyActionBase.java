package io.github.mosps.handlers.actions.party;

import io.github.mosps.handlers.ButtonHandler;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.session.SessionData;
import io.github.mosps.session.SessionManager;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public abstract class PartyActionBase implements ButtonHandler {

    protected SessionData getSession(ButtonInteractionEvent event, String[] customId) {
        String sessionId = customId[2];

        SessionData session = SessionManager.getSession(sessionId);

        if (session == null) {
            event.reply("期限切れの募集です。").setEphemeral(true).queue();
            return null;
        }
        if (session.isClosed()) {
            event.reply("締め切られた募集です。").setEphemeral(true).queue();
            return null;
        }

        return session;
    }

    protected void updateMessage(ButtonInteractionEvent event, SessionData session) {
        event.editMessageEmbeds(
                MessageRenderer.render(session)
        ).setComponents(
                MessageRenderer.buttons(session)
        ).queue();
    }
}
