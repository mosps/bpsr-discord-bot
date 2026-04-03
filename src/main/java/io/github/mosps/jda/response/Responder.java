package io.github.mosps.jda.response;

import io.github.mosps.ui.render.RenderResult;
import net.dv8tion.jda.api.modals.Modal;

public interface Responder {
    void update(RenderResult render, String messageId);
    void update(RenderResult render);
    void reply(RenderResult render);
    void openModal(Modal modal);
    void ephemeral(String message);
    void ephemeral(RenderResult render);
    void error(String message);
}
