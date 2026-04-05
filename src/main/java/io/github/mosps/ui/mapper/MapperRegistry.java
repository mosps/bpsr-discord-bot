package io.github.mosps.ui.mapper;

import io.github.mosps.model.party.Party;
import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.imagine.ImagineEditSession;
import io.github.mosps.ui.mapper.party.PartyDeleteMapper;
import io.github.mosps.ui.mapper.party.PartyMapper;
import io.github.mosps.ui.mapper.party.PartySettingMapper;
import io.github.mosps.ui.mapper.profile.ProfileMapper;
import io.github.mosps.ui.mapper.profile.imagine.ImagineMapper;
import io.github.mosps.ui.views.party.PartyView;
import io.github.mosps.ui.views.party.setting.PartyDeleteView;
import io.github.mosps.ui.views.party.setting.PartySettingView;
import io.github.mosps.ui.views.profile.ProfileView;
import io.github.mosps.ui.views.profile.imagine.ImagineEditView;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {

    private static final Map<Key<?, ?>, Mapper<?, ?>> mappers = new HashMap<>();

    static {
        register(Party.class, PartyView.class, new PartyMapper());
        register(Party.class, PartySettingView.class, new PartySettingMapper());
        register(Party.class, PartyDeleteView.class, new PartyDeleteMapper());
        register(Profile.class, ProfileView.class, new ProfileMapper());
        register(ImagineEditSession.class, ImagineEditView.class, new ImagineMapper());
    }

    public static <T, V> void register(Class<T> model, Class<V> view, Mapper<T, V> mapper) {
        mappers.put(new Key<>(model, view), mapper);
    }

    @SuppressWarnings("unchecked")
    public static <T, V> Mapper<T, V> getMapper(T model, Class<V> view) {
        return (Mapper<T, V>) mappers.get(new Key<>((Class<T>) model.getClass(), view));
    }

    public record Key<T, V> (Class<T> model, Class<V> view) {}
}
