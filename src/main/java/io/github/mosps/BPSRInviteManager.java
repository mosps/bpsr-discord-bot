package io.github.mosps;

import io.github.mosps.jda.listener.MessageButtonListener;
import io.github.mosps.jda.listener.ModalListener;
import io.github.mosps.jda.listener.SelectMenuListener;
import io.github.mosps.jda.listener.SlashCommandListener;
import io.github.mosps.model.profile.ProfileManager;
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
        jda.getGuilds().forEach(guild ->
                guild.updateCommands().addCommands(
                        Commands.slash("party", "パーティ用コマンド")
                                .addSubcommands(
                                        new SubcommandData("create", "パーティを作成"),
                                        new SubcommandData("admin", "初期メッセージを送信")),
                        Commands.slash("profile", "プロフィール用コマンド")
                                .addSubcommands(
                                        new SubcommandData("admin", "初期メッセージを送信"))
                ).queue()
        );

        ProfileManager.loadAll();
    }
}

/*TODO
・Party,Profileリスト作成..?
・各Managerの責務を再確認
・海武器所持ボタン追加

・bot再起動時sessionの保存　(定期保存、クラッシュ対応)
・Partyの期限切れ時メッセージ削除
・Party完全削除ボタン

複数サーバー仕様　(未定）
*/