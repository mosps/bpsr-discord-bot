package io.github.mosps.actions.party.setting;

import io.github.mosps.actions.Action;
import io.github.mosps.actions.ActionContext;
import io.github.mosps.actions.ActionResult;
import io.github.mosps.model.party.Party;
import io.github.mosps.model.party.PartyManager;

public class PartyDeleteAcceptAction implements Action {

    @Override
    public ActionResult execute(ActionContext context) {
        Party party = PartyManager.getParty(context.getCustomId().get("partyId"));
        if (party == null) {
            return ActionResult.of()
                    .error("このパーティは期限切れです。");
        }

        if (context.getUserId() != party.getOwnerId()) {
            return ActionResult.of()
                    .error("パーティ作成者ではありません。");
        }

        PartyManager.deleteParty(party.getPartyId());
        PartyManager.saveParty(party);

        return ActionResult.of().deleteSource().deleteAll(party.getMessageId()).withEphemeral("パーティを削除しました。", 5);
    }
}
