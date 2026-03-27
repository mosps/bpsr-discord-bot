package io.github.mosps.util.customid;

import java.util.HashMap;
import java.util.Map;

public enum CustomIdDomain {

    PARTY("party") {
        @Override
        public String build(String action, Map<String, String> args) {
            return prefix + ":" + action + ":" +
                    args.get("partyId");
        }

        @Override
        public Map<String, String> parse(String[] args) {
            Map<String, String> map = new HashMap<>();
            map.put("partyId", args[0]);
            return map;
        }
    },

    PROFILE("profile") {
        public String build(String action, Map<String, String> args) {
            return prefix + ":" + action + ":" +
                    args.get("type") + "|" + args.get("ownerId");
        }

        @Override
        public Map<String, String> parse(String[] args) {
            Map<String, String> map = new HashMap<>();
            map.put("type", args[0]);
            map.put("ownerId", args[1]);
            return map;
        }
    };

    protected final String prefix;

    CustomIdDomain(String prefix) {
        this.prefix = prefix;
    }

    public abstract String build(String action, Map<String, String> args);
    public abstract Map<String, String> parse(String[] args);

    public static CustomIdDomain from(String prefix) {
        for (CustomIdDomain domain : values()) {
            if (domain.prefix.equals(prefix)) return domain;
        }
        throw new IllegalArgumentException("Unknown domain: " + prefix);
    }
}
