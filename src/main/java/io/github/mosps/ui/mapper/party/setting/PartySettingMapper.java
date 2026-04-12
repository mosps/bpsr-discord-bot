package io.github.mosps.ui.mapper.party.setting;

import io.github.mosps.model.party.Party;
import io.github.mosps.ui.mapper.Mapper;
import io.github.mosps.ui.views.party.setting.PartySettingView;

public class PartySettingMapper implements Mapper<Party, PartySettingView> {

    @Override
    public PartySettingView map(Party party) {
        PartySettingView view = new PartySettingView();

        view.partyId = party.getPartyId();

        return view;
    }
}
