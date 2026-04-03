package io.github.mosps.ui.mapper;

public interface Mapper<T, V> {
    V map(T model);
}
