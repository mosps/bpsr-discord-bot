package io.github.mosps.mapper;

public interface ViewMapper<T, V> {
    V map(T source);
}
