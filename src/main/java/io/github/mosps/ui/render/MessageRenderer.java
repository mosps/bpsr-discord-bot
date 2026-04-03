package io.github.mosps.ui.render;

public class MessageRenderer {

    public static <T> RenderResult render(T view) {
        Renderer<T> renderer = RendererRegistry.getRenderer(view);

        return renderer.render(view);
    }
}
