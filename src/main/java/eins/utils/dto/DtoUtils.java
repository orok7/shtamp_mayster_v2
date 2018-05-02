/*
 * DtoUtils
 *
 * Version 1.0-SNAPSHOT
 *
 * 02.05.18
 *
 * Written by Orok.Eins
 * */

package eins.utils.dto;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DtoUtils {

    public static <T> T convertToDto(Object[] entity, Class<T> dtoType) throws IllegalAccessException, InstantiationException {
        T dto = dtoType.newInstance();
        List<Field> annotatedFields = Arrays.stream(dtoType.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Relation.class))
                .collect(Collectors.toList());
        annotatedFields
                .forEach(f ->
                        System.out.println(f.getType().getSimpleName() + " " +
                        f.getName())
                );
        return dto;
    }

}
