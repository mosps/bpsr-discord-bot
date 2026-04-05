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
・Party,Profileリスト作成..?
・各Managerの責務を再確認
・海武器所持ボタン追加
・responder,delete()同様updateを複数メッセージに対してできるように。
・modal前要素表示
・imaginesession時間経過削除

・bot再起動時sessionの保存　(定期保存、クラッシュ対応)
・Partyの期限切れ時メッセージ削除

複数サーバー仕様　(未定）
*/