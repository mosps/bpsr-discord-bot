package io.github.mosps.views.profile.imagine;

import io.github.mosps.data.Imagines;

import java.util.Map;
import java.util.Set;

public class ImagineEditView {
    public long userId;

    public String add;
    public String remove;
    public String tier;

    public Map<Imagines, String> currentImagines;

    public Map<Imagines, String> addImagines;
    public Map<Imagines, String> removeImagines;
}
