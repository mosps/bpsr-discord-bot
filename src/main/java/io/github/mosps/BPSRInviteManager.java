package io.github.mosps;

import io.github.mosps.handlers.listener.MessageButtonListener;
import io.github.mosps.handlers.listener.ModalListener;
import io.github.mosps.handlers.listener.SelectMenuListener;
import io.github.mosps.handlers.listener.SlashCommandListener;
import io.github.mosps.profile.ProfileManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class BPSRInviteManager {

    public void botStart(String[] args) {
        String token = args[0];

        JDA jda = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS)
                .addEventListeners(
                        new SlashCommandListener(),
                        new MessageButtonListener(),
                        new SelectMenuListener(),
                        new ModalListener()
                )
                .disableCache(
                        CacheFlag.VOICE_STATE,
                        CacheFlag.EMOJI,
                        CacheFlag.STICKER,
                        CacheFlag.SCHEDULED_EVENTS
                )
                .setActivity(Activity.playing("デバッグ中"))
                .setRawEventsEnabled(true)
                .build();
        jda.updateCommands().addCommands(
                Commands.slash("party", "パーティ用コマンド")
                        .addSubcommands(
                                new SubcommandData("create", "パーティを作成"),
                                new SubcommandData("admin", "初期メッセージを送信")),
                Commands.slash("profile", "プロフィール用コマンド")
                        .addSubcommands(
                                new SubcommandData("create", "プロフィールを作成"),
                                new SubcommandData("admin", "初期メッセージを送信"))
        );

        ProfileManager.loadAll();
    }
}

/*TODO
Profile
装備イマジンを選択するように
海武器所持ボタン追加
*/