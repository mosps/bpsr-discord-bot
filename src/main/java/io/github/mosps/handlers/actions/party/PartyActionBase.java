package io.github.mosps.handlers.actions.party;

import io.github.mosps.handlers.handler.ButtonHandler;
import io.github.mosps.render.MessageRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public abstract class PartyActionBase implements ButtonHandler {

    protected Party getSession(ButtonInteractionEvent event, String[] customId) {
        String sessionId = customId[2];

        Party session = PartyManager.getSession(sessionId);

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

    protected void updateMessage(ButtonInteractionEvent event, Party session) {
        RenderResult renderResult = MessageRenderer.render(session);

        event.editMessageEmbeds(
                renderResult.getEmbed()
        ).setComponents(
                renderResult.getComponents()
        ).queue();
    }
}
