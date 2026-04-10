package io.github.mosps;

import io.github.mosps.jda.listener.*;
import io.github.mosps.model.profile.ProfileManager;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class BPSRInviteManager {

    public void botStart(String[] args) {
        String token = args[0];

        JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS)
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

        ProfileManager.loadAll();
    }
}

/*TODO
不具合修正
・party close処理を同期化
    優先度:高

準不具合修正
・imagineSession時間経過削除
    優先度:高

コード整理
・各Managerの責務を再確認(PartyManager済)
    優先度:低

機能追加
・Party,Profileリスト作成..?
    優先度:中
・Partyの期限切れ時メッセージ削除
    優先度:低
・Partyの時間をテキストから取得、期限を自動設定(取得不可、未設定のものは1週間で統一）
    優先度:中
・パーティ設定から手動で参加者を登録できるように。
    優先度:中
・海武器所持ボタン追加
    優先度:低

複数サーバー仕様
・プロフィールにギルドid、channelIdを登録(partyRenderer修正のみ）
    優先度:高

未定
・responderをdelete()同様updateを複数メッセージに対してできるように。
    サーバー単位設計で完全個別管理予定、今後必要がでたら公開パーティ等も考える
・ロール制限プリセット時全体制限の追加
    リファクタのリスク高め、エラーが出てからでも遅くない、現状パーティ操作の同期化をしたので発生リスクは低
*/