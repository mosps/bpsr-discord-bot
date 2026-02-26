package io.github.mosps;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class BPSRInviteManager {

    public void botStart(String[] args) {
        String token = args[0];

        JDA jda = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS)
               .addEventListeners(

               )
               .setActivity(Activity.playing("デバッグ中"))
               .setRawEventsEnabled(true)
               .build();
        jda.upsertCommand("p", "テスト").queue();
    }
}
