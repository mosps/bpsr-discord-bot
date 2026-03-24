package io.github.mosps.util.customid;

import java.util.HashMap;
import java.util.Map;

public enum CustomIdDomain {

    PARTY("party") {
        @Override
        public Map<String, String> parse(String[] args) {
            Map<String, String> map = new HashMap<>();
            map.put("party", args[0]);
            return map;
        }
    },

    PROFILE("profile") {
        @Override
        public Map<String, String> parse(String[] args) {
            Map<String, String> map = new HashMap<>();
            map.put("type", args[0]);
            map.put("owner", args[1]);
            return map;
        }
    };

    private final String prefix;

    CustomIdDomain(String prefix) {
        this.prefix = prefix;
    }

    public abstract Map<String, String> parse(String[] args);

    public static CustomIdDomain from(String prefix) {
        for (CustomIdDomain domain : values()) {
            if (domain.prefix.equals(prefix)) return domain;
        }
        throw new IllegalArgumentException("Unknown domain: " + prefix);
    }
}
