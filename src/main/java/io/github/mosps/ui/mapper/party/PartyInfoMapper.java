package io.github.mosps.ui.mapper.party;

import io.github.mosps.model.data.Classes;
import io.github.mosps.model.data.Imagines;
import io.github.mosps.model.party.Party;
import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.ProfileManager;
import io.github.mosps.ui.mapper.Mapper;
import io.github.mosps.ui.views.party.PartyInfoView;
import io.github.mosps.ui.views.profile.MemberView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartyInfoMapper implements Mapper<Party, PartyInfoView> {

    @Override
    public PartyInfoView map(Party party) {
        PartyInfoView view = new PartyInfoView();

        view.members = getMemberView(party);

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
                    memberView.imagines = getImagineView(profile);

                    return memberView;
                }).toList();
    }

    private static String getImagineView(Profile profile) {
        Map<Imagines, String> imagines = profile.getImagines();

        return imagines.isEmpty()
                ? ""
                : imagines.entrySet().stream()
                .map(entry -> entry.getKey().getDisplay() + entry.getValue())
                .collect(Collectors.joining(""));
    }
}
