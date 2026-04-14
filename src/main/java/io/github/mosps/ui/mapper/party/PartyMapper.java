package io.github.mosps.ui.mapper.party;

import io.github.mosps.model.data.Classes;
import io.github.mosps.model.data.Imagines;
import io.github.mosps.model.party.Party;
import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.ProfileManager;
import io.github.mosps.ui.mapper.Mapper;
import io.github.mosps.ui.views.party.PartyView;
import io.github.mosps.ui.views.profile.MemberView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartyMapper implements Mapper<Party, PartyView> {

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
        view.ownerName = ProfileManager.getProfile(view.ownerId).getName();
        view.members = getMemberView(party);
        view.role = party.getPreset().getValue();
        view.closed = party.isClosed();

        return view;
    }

    private static List<MemberView> getMemberView(Party party) {
        return party.getMembers().stream()
                .map(id -> {
                    Profile profile = ProfileManager.getProfile(id);
                    Classes main = profile.getMainClass();

                    MemberView memberView = new MemberView();

                    memberView.userId = id;
                    memberView.name = profile.getName();
                    memberView.role = main.getRole();
                    memberView.emoji = main.getEmoji();
                    memberView.style = main.getStyle();
                    memberView.imagines = getEquippedImagineView(profile);

                    return memberView;
                }).toList();
    }

    private static String getEquippedImagineView(Profile profile) {
        Map<Imagines, String> equippedImagines = profile.getEquippedImagines();

        return equippedImagines.isEmpty()
                ? ""
                : equippedImagines.entrySet().stream()
                .map(entry -> entry.getKey().getDisplay() + entry.getValue())
                .collect(Collectors.joining(""));
    }
}
