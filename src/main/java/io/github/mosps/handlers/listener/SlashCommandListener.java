package io.github.mosps.handlers.listener;

import io.github.mosps.party.Party;
import io.github.mosps.party.PartyManager;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("party")) {
            event.reply("パーティ募集を作成しました。")
                    .addComponents(ActionRow.of(
                            Button.success("party:join", "参加"),
                            Button.danger("party:leave", "退出"),
                            Button.secondary("party:close", "終了"))
                    ).queue(interactionHook -> {
                        interactionHook.retrieveOriginal().queue(message -> {
                            Party session = new Party(event.getUser().getIdLong(), message.getIdLong());

                            PartyManager.register(session.getPartyId(), session);
                        });
                    });
        }
    }
}
