package io.github.mosps.ui.render.util;

import java.util.List;

public class PageManager {
    private static final int SIZE = 25;

    public static <T> List<T> getPage(List<T> list, int page) {
        int start = page * SIZE;

        if (start >= list.size()) {
            return List.of();
        }

        int end = Math.min(start + SIZE, list.size());

        return list.subList(start, end);
    }

    public static int maxPage(int totalSize) {
        return (int) Math.ceil((double) totalSize / (double) SIZE);
    }
}
