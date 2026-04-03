package io.github.mosps.ui.mapper.profile;

import io.github.mosps.model.data.Classes;
import io.github.mosps.model.data.Imagines;
import io.github.mosps.model.profile.Profile;
import io.github.mosps.ui.mapper.Mapper;
import io.github.mosps.ui.views.profile.ProfileView;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfileMapper implements Mapper<Profile, ProfileView> {

    @Override
    public ProfileView map(Profile profile) {
        ProfileView view = new ProfileView();

        Classes classes = profile.getMainClass();
        Set<Classes> subClasses = profile.getSubClasses();
        Map<Imagines, String> equippedImagines = profile.getEquippedImagines();
        Map<Imagines, String> imagines = profile.getImagines();

        view.ownedImagines = imagines;

        view.userId = profile.getUserId();
        view.name = profile.getName();
        view.mainClass = classes != null
                ? classes.getDisplay()
                : "未設定";
        view.subClasses = subClasses.isEmpty()
                ? "未設定"
                : subClasses.stream()
                .map(Classes::getDisplay)
                .collect(Collectors.joining("\n"));
        view.equippedImagines = equippedImagines.isEmpty()
                ? "未設定"
                : equippedImagines.entrySet().stream()
                .map(entry -> entry.getKey().getDisplay() + entry.getValue())
                .collect(Collectors.joining(""));
        view.imagines = imagines.isEmpty()
                ? "未設定"
                : imagines.entrySet().stream()
                .map(entry -> entry.getKey().getDisplay() + entry.getValue())
                .collect(Collectors.joining(""));

        return view;
    }
}
