package io.github.mosps.handlers.actions.data;

import java.io.Serializable;
import java.util.Map;

public class ModalData implements ActionData {
    private final Map<String,String> values;

    public ModalData(Map<String, String> values) {
        this.values = values;
    }

    public Map<String, String> get() {
        return values;
    }
}
