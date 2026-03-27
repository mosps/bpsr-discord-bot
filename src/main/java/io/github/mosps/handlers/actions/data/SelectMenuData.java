package io.github.mosps.handlers.actions.data;

import io.github.mosps.handlers.actions.Action;

import java.io.Serializable;
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
