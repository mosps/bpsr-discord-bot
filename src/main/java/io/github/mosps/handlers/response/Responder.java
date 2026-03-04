package io.github.mosps.handlers.response;

import io.github.mosps.render.RenderResult;

public interface Responder {
    void update(RenderResult render);
    void ephemeral(String message);
}
