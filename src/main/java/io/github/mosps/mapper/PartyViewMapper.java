package io.github.mosps.mapper;

import io.github.mosps.party.Party;
import io.github.mosps.views.party.PartyView;

public class PartyViewMapper implements ViewMapper<Party, PartyView> {

    @Override
    public PartyView map(Party party) {
        PartyView view = new PartyView();

        view.destination = party.getDestination() != null
                ? party.getDestination()
                : "-";
        view.time = party.getTime() != null
                ? party.getTime()
                : "-";
        view.note = party.getNote() != null
                ? party.getNote()
                : "-";

        view.partyId = party.getPartyId();
        view.ownerId = party.getOwnerId();
        view.members = party.getMembers();
        view.role = party.getPreset().getValue();
        view.closed = party.isClosed();

        return view;
    }
}
