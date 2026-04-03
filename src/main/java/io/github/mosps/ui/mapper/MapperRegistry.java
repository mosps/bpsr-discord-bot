package io.github.mosps.ui.mapper;

import io.github.mosps.model.party.Party;
import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.imagine.ImagineEditSession;
import io.github.mosps.ui.mapper.party.PartyDeleteMapper;
import io.github.mosps.ui.mapper.party.PartyMapper;
import io.github.mosps.ui.mapper.party.PartySettingMapper;
import io.github.mosps.ui.mapper.profile.ProfileMapper;
import io.github.mosps.ui.mapper.profile.imagine.ImagineMapper;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {

    private static final Map<Class<?>, Mapper<?, ?>> mappers = new HashMap<>();

    static {
        register(Party.class, new PartyMapper());
        register(Party.class, new PartySettingMapper());
        register(Party.class, new PartyDeleteMapper());
        register(Profile.class, new ProfileMapper());
        register(ImagineEditSession.class, new ImagineMapper());
    }

    public static <T, V> void register(Class<T> model, Mapper<T, V> mapper) {
        mappers.put(model, mapper);
    }

    @SuppressWarnings("unchecked")
    public static <T, V> Mapper<T, V> getMapper(T model) {
        return (Mapper<T, V>) mappers.get(model.getClass());
    }
}
