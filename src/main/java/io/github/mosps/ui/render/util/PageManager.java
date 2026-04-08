package io.github.mosps.ui.render.util;

import java.util.List;

public class PageManager {
    private static final int SIZE = 25;

    public static <T> List<T> getPage(List<T> list, int page) {
        int start = page * SIZE;
        int end = Math.min(start + SIZE, list.size());

        if (start >= list.size()) {
            return List.of();
        }

        return list.subList(start, end);
    }

    public static int totalPage(int totalSize) {
        return Math.max(1, (int) Math.ceil((double) totalSize / (double) SIZE));
    }

    public static int maxIndex(int totalSize) {
        return totalPage(totalSize) - 1;
    }

    public static boolean hasNext(int page, int totalSize) {
        return page < maxIndex(totalSize);
    }

    public static boolean hasPrev(int page) {
        return page > 0;
    }
}
