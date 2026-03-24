package io.github.mosps.util.customid;

import java.util.HashMap;
import java.util.Map;

public class CustomIdBuilder {

    private final CustomIdDomain domain;
    private final String action;
    private final Map<String, String> args = new HashMap<>();

    public CustomIdBuilder(CustomIdDomain domain, String action) {
        this.domain = domain;
        this.action = action;
    }

    public CustomIdBuilder put(String key, String value) {
        args.put(key, value);
        return this;
    }

    public String build() {
        return domain.build(action, args);
    }
}
