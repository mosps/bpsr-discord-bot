package io.github.mosps.ui.views.profile;

import io.github.mosps.model.data.Imagines;

import java.util.Map;

public class ProfileView {
    public long userId;
    public String name;

    public String mainClass;
    public String subClasses;

    public String equippedImagines;
    public String imagines;

    public Map<Imagines, String> ownedImagines;
}
