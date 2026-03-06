package io.github.mosps.profile;

import java.util.Set;

public class Profile {

    private final long userId;
    private String name;

    private String mainClass;
    private Set<String> subClasses;

    private Set<String> imagines;

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

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public Set<String> getsubClasses() {
        return subClasses;
    }

    public void addSubClass(String subClass) {
        this.subClasses.add(subClass);
    }

    public Set<String> getImagines() {
        return imagines;
    }

    public void addImagine(String imagine) {
        this.imagines.add(imagine);
    }
}
