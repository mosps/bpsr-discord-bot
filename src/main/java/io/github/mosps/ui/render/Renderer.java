package io.github.mosps.ui.render;

public interface Renderer<T> {
    RenderResult render(T message);
}
