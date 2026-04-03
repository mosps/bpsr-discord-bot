package io.github.mosps.actions.data;

import java.util.Map;

public class ModalData implements ActionData {
    private final Map<String,String> values;

    public ModalData(Map<String, String> values) {
        this.values = values;
    }

    public String get(String key) {
        return values.get(key);
    }
}
