package io.github.mosps.mapper;

import io.github.mosps.party.Party;
import io.github.mosps.views.party.setting.PartySettingView;

public class PartySettingMapper implements Mapper<Party, PartySettingView> {

    @Override
    public PartySettingView map(Party party) {
        PartySettingView view = new PartySettingView();

        view.partyId = party.getPartyId();

        return view;
    }
}
