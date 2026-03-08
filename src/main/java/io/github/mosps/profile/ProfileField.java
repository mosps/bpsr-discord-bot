package io.github.mosps.profile;

import io.github.mosps.data.Classes;
import io.github.mosps.data.Imagines;

public enum ProfileField {

    MAIN_CLASS("main_class") {
        @Override
        public void apply(Profile profile, String value) {
            profile.setMainClass(Classes.valueOf(value));
        }

        @Override
        public void reset(Profile profile) {
        }
    },

    SUB_CLASS("sub_class") {
        @Override
        public void apply(Profile profile, String value) {
            profile.addSubClass(Classes.valueOf(value));
        }

        @Override
        public void reset(Profile profile) {
            profile.resetSubClasses();
        }
    },

    IMAGINE("imagine") {
        @Override
        public void apply(Profile profile, String value) {
            profile.addImagine(Imagines.valueOf(value));
        }

        @Override
        public void reset(Profile profile) {
        }
    };

    private final String id;

    ProfileField(String id) {
        this.id = id;
    }

    public abstract void apply(Profile profile, String value);

    public abstract void reset(Profile profile);

    public String getId() {
        return id;
    }

    public static ProfileField fromId(String id) {
        for (ProfileField field : values()) {
            if (field.id.equals(id)) {
                return field;
            }
        }
        return null;
    }
}

