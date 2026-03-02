package io.github.mosps.render;

public class MessageRenderer {

    public static <T> RenderResult render(T view) {
        Renderer<T> renderer = RendererRegistry.getRenderer(view);

        return renderer.render(view);
    }
}
