package io.github.mosps.actions.data;

import java.util.List;

public class SelectMenuData implements ActionData {
    private final List<String> values;

    public SelectMenuData(List<String> values) {
        this.values = values;
    }

    public List<String> get() {
        return values;
    }
}
