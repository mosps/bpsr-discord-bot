package io.github.mosps.mapper;

import io.github.mosps.party.Party;
import io.github.mosps.views.party.setting.PartyDeleteView;

public class PartyDeleteMapper implements Mapper<Party, PartyDeleteView> {

    @Override
    public PartyDeleteView map(Party party) {
        PartyDeleteView view = new PartyDeleteView();

        view.partyId = party.getPartyId();

        return  view;
    }
}
