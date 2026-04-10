package io.github.mosps.util.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuildMemberStorage {

    private static final Gson gson = new Gson();
    private static final Path file = Paths.get("data").resolve("guild_members.json");

    private static final Logger logger = Logger.getLogger(GuildMemberStorage.class.getName());
    private static final Type TYPE = new TypeToken<Map<Long, Set<Long>>>() {}.getType();

    public static void save(Map<Long, Set<Long>> guildMembers) {
        try {
            Files.createDirectories(file.getParent());

            String json = gson.toJson(guildMembers);

            Files.writeString(file, json);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not save guild members to file", e);
        }
    }

    public static Map<Long, Set<Long>> loadAll() {
        try {
            if (!Files.exists(file)) return new HashMap<>();

            String json = Files.readString(file);

            Map<Long, Set<Long>> data = gson.fromJson(json, TYPE);

            if (data == null) return new HashMap<>();

            data.replaceAll((k, v) -> v != null ? v: new HashSet<>());

            return data;
        } catch (IOException e)  {
            logger.log(Level.WARNING, "Could not load guild members from file", e);
        }

        return new HashMap<>();
    }
}
