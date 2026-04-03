package io.github.mosps.ui.mapper.party;

import io.github.mosps.model.party.Party;
import io.github.mosps.ui.mapper.Mapper;
import io.github.mosps.ui.views.party.setting.PartyDeleteView;

public class PartyDeleteMapper implements Mapper<Party, PartyDeleteView> {

    @Override
    public PartyDeleteView map(Party party) {
        PartyDeleteView view = new PartyDeleteView();

        view.partyId = party.getPartyId();

        return  view;
    }
}
