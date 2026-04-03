package io.github.mosps.model.profile;

import io.github.mosps.model.data.Classes;
import io.github.mosps.model.data.Imagines;

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

    EQUIPPED_IMAGINES("equipped_imagines") {
        @Override
        public void apply(Profile profile, String value) {
            String[] split = value.split(":");
            Imagines imagines = Imagines.valueOf(split[0]);
            String tier = split[1];

            profile.addEquippedImagines(imagines, tier);
        }

        @Override
        public void reset(Profile profile) {
            profile.resetEquippedImagines();
        }
    },

    IMAGINE_ADD("imagine_add") {
        @Override
        public void apply(Profile profile, String value) {
            String[] split = value.split(":");
            Imagines imagines = Imagines.valueOf(split[0]);
            String tier = split[1];

            profile.addImagine(imagines, tier);
        }

        @Override
        public void reset(Profile profile) {
        }
    },

    IMAGINE_REMOVE("imagine_remove") {
        @Override
        public void apply(Profile profile, String value) {
            profile.removeImagine(Imagines.valueOf(value));
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
            if (field.id.equals(id)) return field;
        }
        return null;
    }
}

