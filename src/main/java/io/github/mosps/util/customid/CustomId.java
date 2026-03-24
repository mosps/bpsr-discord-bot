package io.github.mosps.util.customid;

import java.util.HashMap;
import java.util.Map;

public class CustomId {

    private final String Key;
    private final Map<String, String> args;

    public CustomId(String key, Map<String, String> args) {
        this.Key = key;
        this.args = args;
    }

    public static CustomId of(String raw) {
        String[] split = raw.split(":", 3);

        if (split.length < 2) {
            throw new IllegalArgumentException("Invalid customId: " + raw);
        }

        String domainKey = split[0];
        String key = split[0] + ":" + split[1];
        String[] rawArgs = split.length == 3
                ? split[2].split("\\|")
                : new String[0];

        CustomIdDomain domain = CustomIdDomain.from(domainKey);

        Map<String, String> args = domain.parse(rawArgs);

        return new CustomId(key, args);
    }

    public String get(String mapKey) {
        return args.get(mapKey);
    }

    public String getKey() {
        return Key;
    }
}

