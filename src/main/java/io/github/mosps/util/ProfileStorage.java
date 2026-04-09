package io.github.mosps.util;

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
    private static final Path basePath = Paths.get("data");

    private static final Logger logger = Logger.getLogger(ProfileStorage.class.getName());

    private static Path getGuilDPath(long guildId) {
        return basePath.resolve("guild_" + guildId).resolve("profiles");
    }

    public static void save(long guildId, Profile profile) {
        try {
            Path dir = getGuilDPath(guildId);
            Files.createDirectories(dir);

            Path file = dir.resolve(profile.getUserId() + ".json");
            String json = gson.toJson(profile);

            Files.writeString(file, json);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not save profile to file", e);
        }
    }

    public static Profile load(long guildId, long userId) {
        try {
            Path dir = getGuilDPath(guildId);
            Path file = dir.resolve(userId + ".json");

            if (!Files.exists(file)) return null;

            String json = Files.readString(file);

            return gson.fromJson(json, Profile.class);
        } catch (IOException e)  {
            logger.log(Level.WARNING, "Could not load profile from file", e);
        }

        return null;
    }

    public static Map<Long, Profile> loadGuild(long guildId) {
        Map<Long, Profile> profiles = new HashMap<>();

        Path dir = getGuilDPath(guildId);

        try {
            if (!Files.exists(dir)) return profiles;

            Files.createDirectories(dir);

            try (Stream<Path> paths = Files.list(dir)) {
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
