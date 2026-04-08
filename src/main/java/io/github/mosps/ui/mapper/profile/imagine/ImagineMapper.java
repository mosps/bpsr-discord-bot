package io.github.mosps.ui.mapper.profile.imagine;

import io.github.mosps.model.profile.imagine.ImagineEditSession;
import io.github.mosps.ui.mapper.Mapper;
import io.github.mosps.ui.views.profile.imagine.ImagineEditView;

import java.util.stream.Collectors;

public class ImagineMapper implements Mapper<ImagineEditSession, ImagineEditView> {

    @Override
    public ImagineEditView map(ImagineEditSession session) {
        ImagineEditView view = new ImagineEditView();

        view.availableImagines = session.getAvailableImagines();

        view.currentImagines = session.getCurrentImagines();

        view.addImagines = session.getAddImagines();
        view.removeImagines = session.getRemoveImagines();

        view.userId = session.getUserId();
        view.add = view.addImagines.isEmpty()
                ? "-"
                : view.addImagines.entrySet().stream()
                .map(entry -> entry.getKey().getDisplay() + entry.getValue())
                .collect(Collectors.joining(""));
        view.remove = view.removeImagines.isEmpty()
                ? "-"
                : view.removeImagines.entrySet().stream()
                .map(entry -> entry.getKey().getDisplay() + entry.getValue())
                .collect(Collectors.joining(""));
        view.tier = session.getTier() == null
                ? "0凸"
                : session.getTier();
        view.page = session.getPage();

        return view;
    }
}
