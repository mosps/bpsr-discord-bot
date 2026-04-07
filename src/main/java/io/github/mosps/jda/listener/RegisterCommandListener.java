package io.github.mosps.jda.listener;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class RegisterCommandListener extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        event.getJDA().getGuilds().forEach(guild ->
                guild.updateCommands().addCommands(
                        Commands.slash("party", "パーティ用コマンド")
                                .addSubcommands(
                                        new SubcommandData("admin", "初期メッセージを送信")
                                ),
                        Commands.slash("profile", "プロフィール用コマンド")
                                .addSubcommands(
                                        new SubcommandData("admin", "初期メッセージを送信")
                                )
                ).queue()
        );
    }
}
