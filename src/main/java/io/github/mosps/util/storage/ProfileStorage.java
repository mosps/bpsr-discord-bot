package io.github.mosps.util.storage;

import com.google.gson.Gson;
import io.github.mosps.model.profile.Profile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ProfileStorage {

    private static final Gson gson = new Gson();
    private static final Path basePath = Paths.get("data").resolve("profiles");

    private static final Logger logger = Logger.getLogger(ProfileStorage.class.getName());

    public static void save(Profile profile) {
        try {
            Files.createDirectories(basePath);

            Path file = basePath.resolve(profile.getUserId() + ".json");
            String json = gson.toJson(profile);

            Files.writeString(file, json);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not save profile to file", e);
        }
    }

    public static Profile load(long userId) {
        try {
            Path file = basePath.resolve(userId + ".json");

            if (!Files.exists(file)) return null;

            String json = Files.readString(file);

            return gson.fromJson(json, Profile.class);
        } catch (IOException e)  {
            logger.log(Level.WARNING, "Could not load profile from file", e);
        }

        return null;
    }

    public static Map<Long, Profile> loadAll() {
        Map<Long, Profile> profiles = new HashMap<>();

        try {
            Files.createDirectories(basePath);

            try (Stream<Path> paths = Files.list(basePath)) {
                paths.forEach(file -> {
                    try {
                        String json = Files.readString(file);
                        Profile profile = gson.fromJson(json, Profile.class);
                        profiles.put(profile.getUserId(), profile);
                    } catch (IOException e) {
                        logger.log(Level.WARNING, "Could not load profile from file", e);
                    }
                });
            }
        }  catch (IOException e) {
            logger.log(Level.WARNING, "Could not load profiles", e);
        }

        return profiles;
    }
}
