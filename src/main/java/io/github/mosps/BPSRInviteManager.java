package io.github.mosps;

public class BPSRInviteManager {

    public void botStart(String[] args) {
        String token = args[0];

        try {
             jda = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS)
                    .addEventListeners(
                            ImagineChecker(),
                            CommandListener()
                    )
                    .setActivity(Activity.playing("/bic"))
                    .setRawEventsEnabled(true)
                    .build()
        }
    }
}
