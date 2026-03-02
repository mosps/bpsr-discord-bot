package io.github.mosps.render;

public interface Renderer<T> {
    RenderResult render(T message);
}
