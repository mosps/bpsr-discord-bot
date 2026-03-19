package io.github.mosps.profile.imagine;

import io.github.mosps.data.Imagines;

public enum ImagineEditField {

    ADD("add") {
        @Override
        public void apply(ImagineEditSession session, String value) {
            session.add(Imagines.valueOf(value));
        }
    },

    REMOVE("remove") {
        @Override
        public void apply(ImagineEditSession session, String value) {
            session.remove(Imagines.valueOf(value));
        }
    },

    TIER("tier") {
        @Override
        public void apply(ImagineEditSession session, String value) {
            session.setTier(value);
        }
    };

    private final String id;

    ImagineEditField(String id) {
        this.id = id;
    }

    public abstract void apply(ImagineEditSession session, String value);

    public static ImagineEditField fromId(String id) {
        for (ImagineEditField field : values()) {
            if (field.id.equals(id)) return field;
        }
        return null;
    }
}
