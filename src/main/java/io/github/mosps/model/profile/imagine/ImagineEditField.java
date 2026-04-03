package io.github.mosps.model.profile.imagine;

import io.github.mosps.model.data.Imagines;

public enum ImagineEditField {

    ADD("add") {
        @Override
        public void apply(ImagineEditSession session, String value) {
            String[] split = value.split(":");
            Imagines imagines = Imagines.valueOf(split[0]);
            String tier = split[1];

            session.add(imagines, tier);
        }
    },

    REMOVE("remove") {
        @Override
        public void apply(ImagineEditSession session, String value) {
            String[] split = value.split(":");
            Imagines imagines = Imagines.valueOf(split[0]);
            String tier = split[1];

            session.remove(imagines, tier);
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
