package io.github.mosps;

import io.github.mosps.jda.listener.*;
import io.github.mosps.model.party.PartyManager;
import io.github.mosps.model.profile.ProfileManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
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
                        new ModalListener(),
                        new RegisterCommandListener()
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

        PartyManager.loadAll();
        ProfileManager.loadAll();

        PartyManager.initCounter();
    }
}

/*TODO
不具合修正
・party close処理を同期化
・ページ遷移機能の不具合を修正

準不具合修正
・ロール制限プリセット時全体制限の追加
・imagineSession時間経過削除

コード整理
・各Managerの責務を再確認
・絵文字をBOT内に保存

機能追加
・Party,Profileリスト作成..?
・Partyの期限切れ時メッセージ削除
・パーティ設定から手動で参加者を登録できるように。
・海武器所持ボタン追加

複数サーバー仕様
・responder,delete()同様updateを複数メッセージに対してできるように。
・プロフィールにギルドid、channelIdを登録
*/