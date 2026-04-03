package io.github.mosps.model.profile.imagine;

import io.github.mosps.model.data.Imagines;

import java.util.HashMap;
import java.util.Map;

public class ImagineEditSession {
    private final long userId;

    private final Map<Imagines, String> currentImagines = new HashMap<>();

    private final Map<Imagines, String> addImagines = new HashMap<>();
    private final Map<Imagines, String> removeImagines = new HashMap<>();

    private String tier;

    private int page = 0;

    public ImagineEditSession(long userId, Map<Imagines, String> imagines) {
        this.userId = userId;
        currentImagines.putAll(imagines);
    }

    public void add(Imagines imagine, String tier) {
        addImagines.put(imagine, tier);
    }

    public void remove(Imagines imagine, String tier) {
        removeImagines.put(imagine, tier);
    }

    public long getUserId() {
        return userId;
    }

    public Map<Imagines, String> getCurrentImagines() {
        return currentImagines;
    }

    public Map<Imagines, String> getAddImagines() {
        return addImagines;
    }

    public Map<Imagines, String> getRemoveImagines() {
        return removeImagines;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public int getPage() {
        return page;
    }

    public void nextPage() {
        this.page++;
    }

    public void prevPage() {
        this.page--;
    }
}
