package io.github.mosps.views.profile.imagine;

import io.github.mosps.data.Imagines;

import java.util.Set;

public class ImagineEditView {
    public long userId;

    public String add;
    public String remove;
    public String tier;

    public Set<Imagines> currentImagines;

    public Set<Imagines> addImagines;
    public Set<Imagines> removeImagines;
}
