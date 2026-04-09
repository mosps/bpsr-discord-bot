package io.github.mosps.util;

import com.google.gson.Gson;
import io.github.mosps.model.party.Party;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class PartyStorage {

    private static final Gson gson = new Gson();
    private static final Path basePath = Paths.get("data");

    private static final Logger logger = Logger.getLogger(PartyStorage.class.getName());

    private static Path getGuildPath(long guildId) {
        return basePath.resolve("guild_" + guildId).resolve("parties");
    }

    public static void save(long guildId, Party party) {
        try {
            Path dir = getGuildPath(guildId);
            Files.createDirectories(dir);

            Path file = dir.resolve(party.getPartyId() + ".json");
            String json = gson.toJson(party);

            Files.writeString(file, json);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not save party to file", e);
        }
    }

    public static void delete(long guildId, Party party) {
        try {
            Path dir = getGuildPath(guildId);
            Path file = dir.resolve(party.getPartyId() + ".json");

            if (Files.exists(file)) {
                Files.delete(file);
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not delete party to file", e);
        }
    }

    public static Party load(long guildId, long partyId) {
        try {
            Path dir = getGuildPath(guildId);
            Path file = dir.resolve(partyId + ".json");

            if (!Files.exists(file)) return null;

            String json = Files.readString(file);

            return gson.fromJson(json, Party.class);
        } catch (IOException e)  {
            logger.log(Level.WARNING, "Could not load party from file", e);
        }

        return null;
    }

    public static Map<Long, Party> loadGuild(long guildId) {
        Map<Long, Party> parties = new HashMap<>();

        Path dir = getGuildPath(guildId);

        try {
            if (!Files.exists(dir)) return parties;

            try (Stream<Path> paths = Files.list(dir)) {
                paths.forEach(file -> {
                    try {
                        String json = Files.readString(file);
                        Party party = gson.fromJson(json, Party.class);
                        parties.put(party.getPartyId(), party);
                    } catch (IOException e) {
                        logger.log(Level.WARNING, "Could not load party from file", e);
                    }
                });
            }
        }  catch (IOException e) {
            logger.log(Level.WARNING, "Could not load guild parties", e);
        }

        return parties;
    }
}
