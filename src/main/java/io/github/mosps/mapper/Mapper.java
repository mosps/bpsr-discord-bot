package io.github.mosps.mapper;

public interface Mapper<T, V> {
    V map(T source);
}
