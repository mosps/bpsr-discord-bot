package io.github.mosps.profile;

import io.github.mosps.data.Classes;
import io.github.mosps.data.Imagines;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Profile {

    private final long userId;
    private String name;

    private Classes mainClass;
    private Set<Classes> subClasses = new HashSet<>();

    private Map<Imagines, String> equippedImagines = new HashMap<>();
    private Map<Imagines, String> imagines = new HashMap<>();

    public Profile(long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classes getMainClass() {
        return mainClass;
    }

    public void setMainClass(Classes mainClass) {
        this.mainClass = mainClass;
    }

    public Set<Classes> getSubClasses() {
        return subClasses;
    }

    public void addSubClass(Classes subClass) {
        this.subClasses.add(subClass);
    }

    public void resetSubClasses() {
        subClasses.clear();
    }

    public Map<Imagines, String> getEquippedImagines() {
        return equippedImagines;
    }

    public void addEquippedImagines(Imagines equippedImagines, String tier) {
        this.equippedImagines.put(equippedImagines, tier);
    }

    public void resetEquippedImagines() {
        equippedImagines.clear();
    }

    public Map<Imagines, String> getImagines() {
        return imagines;
    }

    public void addImagine(Imagines imagine, String tier) {
        this.imagines.put(imagine, tier);
    }

    public void removeImagine(Imagines imagine) {
        this.imagines.remove(imagine);
    }

    public void resetImagines() {
        imagines.clear();
    }
}
