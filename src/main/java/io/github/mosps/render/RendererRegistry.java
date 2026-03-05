package io.github.mosps.render;

import io.github.mosps.render.party.PartyLobbyRenderer;
import io.github.mosps.render.party.PartyRenderer;
import io.github.mosps.render.profile.ProfileLobbyRenderer;
import io.github.mosps.render.profile.ProfileRenderer;
import io.github.mosps.views.party.PartyLobbyView;
import io.github.mosps.views.party.PartyView;
import io.github.mosps.views.profile.ProfileLobbyView;
import io.github.mosps.views.profile.ProfileView;

import java.util.HashMap;
import java.util.Map;

public class RendererRegistry {

    private static final Map<Class<?>, Renderer<?>> renderers = new HashMap<>();

    static {
        register(PartyView.class, new PartyRenderer());
        register(PartyLobbyView.class, new PartyLobbyRenderer());
        register(ProfileView.class, new ProfileRenderer());
        register(ProfileLobbyView.class, new ProfileLobbyRenderer());
    }

    public static <T> void register(Class<T> view, Renderer<T> renderer) {
        renderers.put(view, renderer);
    }

    @SuppressWarnings("unchecked")
    public static <T> Renderer<T> getRenderer(T view) {
        return (Renderer<T>) renderers.get(view.getClass());
    }
}
