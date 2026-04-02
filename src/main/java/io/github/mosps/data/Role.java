package io.github.mosps.data;

public enum Role {
    TANK("T"),
    HEALER("H"),
    DPS("D"),
    ALL("A");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
