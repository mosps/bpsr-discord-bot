package io.github.mosps.ui.views.profile.imagine;

import io.github.mosps.model.data.Imagines;

import java.util.List;
import java.util.Map;

public class ImagineEditView {
    public long userId;

    public int page;

    public String add;
    public String remove;
    public String tier;

    public List<Imagines> availableImagines;

    public Map<Imagines, String> currentImagines;

    public Map<Imagines, String> addImagines;
    public Map<Imagines, String> removeImagines;
}
