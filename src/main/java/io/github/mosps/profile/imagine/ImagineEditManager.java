package io.github.mosps.profile.imagine;

import io.github.mosps.data.Imagines;
import io.github.mosps.views.profile.imagine.ImagineEditView;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ImagineEditManager {

    private static final Map<Long, ImagineEditSession> sessions = new ConcurrentHashMap<>();

    public static ImagineEditSession get(long userId) {
        return sessions.computeIfAbsent(userId, ImagineEditSession::new);
    }

    public static void remove(long userId) {
        sessions.remove(userId);
    }

    public static ImagineEditView createView(ImagineEditSession session) {
        ImagineEditView view = new ImagineEditView();

        view.addImagines = session.getAddImagines();
        view.removeImagines = session.getRemoveImagines();

        view.userId = session.getUserId();
        view.add = view.addImagines.isEmpty()
                ? "-"
                : view.addImagines.stream()
                .map(Imagines::getDisplay)
                .collect(Collectors.joining(""));
        view.remove = view.removeImagines.isEmpty()
                ? "-"
                : view.removeImagines.stream()
                .map(Imagines::getDisplay)
                .collect(Collectors.joining(""));
        view.tier = session.getTier();

        return view;
    }
}
