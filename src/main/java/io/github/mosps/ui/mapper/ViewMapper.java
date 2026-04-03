package io.github.mosps.ui.mapper;

public class ViewMapper {

    public static <T, V> V map(T model) {
        Mapper<T, V> mapper = MapperRegistry.getMapper(model);

        return mapper.map(model);
    }
}
