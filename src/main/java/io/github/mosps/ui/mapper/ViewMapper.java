package io.github.mosps.ui.mapper;

public class ViewMapper {

    public static <T, V> V map(T model, Class<V> view) {
        Mapper<T, V> mapper = MapperRegistry.getMapper(model, view);

        return mapper.map(model);
    }
}
