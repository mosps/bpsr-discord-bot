package io.github.mosps.ui.mapper.party.setting;

import io.github.mosps.model.party.Party;
import io.github.mosps.model.party.setting.PartyMemberAddManager;
import io.github.mosps.model.party.setting.PartyMemberAddSession;
import io.github.mosps.ui.mapper.Mapper;
import io.github.mosps.ui.views.party.setting.PartySettingView;

public class PartySettingMapper implements Mapper<Party, PartySettingView> {

    @Override
    public PartySettingView map(Party party) {
        PartySettingView view = new PartySettingView();
        PartyMemberAddSession session = PartyMemberAddManager.get(party.getOwnerId(), party.getGuildId());

        view.partyId = party.getPartyId();
        view.page = session.getPage();
        view.members = session.getMembers();

        return view;
    }
}
