package io.github.mosps.util.log;

import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogService {

    public static void log(ActionContext context, ActionResult actionResult) {
        String action = context.getCustomId().getKey();
        String detail = ActionDetailMapper.get(action);
        String time = now();
        String result = actionResult.getErrorMessage() != null
                ? actionResult.getErrorMessage()
                : "SUCCESS";
        String target = context.getCustomId().get("partyId") != null
                ?  "partyId=" + context.getCustomId().get("partyId")
                : "userId=" + context.getCustomId().get("userId");

        if (detail == null) {
            return;
        }

        System.out.printf(
                """
                 %s [Info] guildId=%s %s user=%s %s->%s
                 """.formatted(time, context.getGuildId(), target, context.getName(), detail, result));
    }

    private static String now() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
