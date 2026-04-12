package io.github.mosps.ui.render;

import io.github.mosps.ui.render.party.PartyInfoRenderer;
import io.github.mosps.ui.render.party.PartyLobbyRenderer;
import io.github.mosps.ui.render.party.PartyRenderer;
import io.github.mosps.ui.render.party.setting.PartyDeleteRenderer;
import io.github.mosps.ui.render.party.setting.PartySettingRenderer;
import io.github.mosps.ui.render.profile.ProfileLobbyRenderer;
import io.github.mosps.ui.render.profile.ProfileRenderer;
import io.github.mosps.ui.render.profile.imagine.ImagineEditRenderer;
import io.github.mosps.ui.views.party.PartyInfoView;
import io.github.mosps.ui.views.party.PartyLobbyView;
import io.github.mosps.ui.views.party.PartyView;
import io.github.mosps.ui.views.party.setting.PartyDeleteView;
import io.github.mosps.ui.views.party.setting.PartySettingView;
import io.github.mosps.ui.views.profile.ProfileLobbyView;
import io.github.mosps.ui.views.profile.ProfileView;
import io.github.mosps.ui.views.profile.imagine.ImagineEditView;

import java.util.HashMap;
import java.util.Map;

public class RendererRegistry {

    private static final Map<Class<?>, Renderer<?>> renderers = new HashMap<>();

    static {
        register(PartyView.class, new PartyRenderer());
        register(PartyInfoView.class, new PartyInfoRenderer());
        register(PartySettingView.class, new PartySettingRenderer());
        register(PartyDeleteView.class, new PartyDeleteRenderer());
        register(PartyLobbyView.class, new PartyLobbyRenderer());
        register(ProfileView.class, new ProfileRenderer());
        register(ProfileLobbyView.class, new ProfileLobbyRenderer());
        register(ImagineEditView.class, new ImagineEditRenderer());
    }

    public static <T> void register(Class<T> view, Renderer<T> renderer) {
        renderers.put(view, renderer);
    }

    @SuppressWarnings("unchecked")
    public static <T> Renderer<T> getRenderer(T view) {
        return (Renderer<T>) renderers.get(view.getClass());
    }
}