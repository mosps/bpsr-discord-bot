package io.github.mosps.profile.imagine;

import io.github.mosps.data.Imagines;

import java.util.HashSet;
import java.util.Set;

public class ImagineEditSession {

    private final long userId;

    private final Set<Imagines> addImagines = new HashSet<>();
    private final Set<Imagines> removeImagines = new HashSet<>();

    private String tier;

    public ImagineEditSession(long userId) {
        this.userId = userId;
    }

    public void add(Imagines imagine) {
        addImagines.add(imagine);
        removeImagines.remove(imagine);
    }

    public void remove(Imagines imagine) {
        removeImagines.add(imagine);
        addImagines.remove(imagine);
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public long getUserId() {
        return userId;
    }

    public Set<Imagines> getAddImagines() {
        return addImagines;
    }

    public Set<Imagines> getRemoveImagines() {
        return removeImagines;
    }

    public String getTier() {
        return tier;
    }
}
