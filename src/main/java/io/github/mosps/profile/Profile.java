package io.github.mosps.profile;

import io.github.mosps.data.Classes;
import io.github.mosps.data.Imagines;

import java.util.HashSet;
import java.util.Set;

public class Profile {

    private final long userId;
    private String name;

    private Classes mainClass;
    private Set<Classes> subClasses = new HashSet<>();

    private Set<Imagines> imagines = new HashSet<>();

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

    public Set<Imagines> getImagines() {
        return imagines;
    }

    public void addImagine(Imagines imagine) {
        this.imagines.add(imagine);
    }

    public void removeImagine(Imagines imagine) {
        this.imagines.remove(imagine);
    }

    public void resetImagines() {
        imagines.clear();
    }
}
