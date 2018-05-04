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

import eins.utils.reflect.InstanceUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class DtoUtils {

    public static <T> T convertToDto(Class<T> dtoType, Object... entities) {
        T dto = InstanceUtils.newInstance(dtoType);
        Dto dtoTypeAnnotation = dtoType.getAnnotation(Dto.class);
        final Class<?> appliedOnlyTo;
        if (dtoTypeAnnotation != null) {
            appliedOnlyTo = dtoTypeAnnotation.appliedOnlyTo();
        } else {
            appliedOnlyTo = Void.class;
        }
        List<Field> annotatedFields = Arrays.stream(dtoType.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Relation.class)).collect(Collectors.toList());
        Map<Class<?>, Object> entitiesMap = prepareEntitiesMap(entities);
        if (entitiesMap == null) {
            log.warn("DTO {}.java wasn't created because entities list isn't unique or it's NULL.",
                    dtoType.getSimpleName());
            return null;
        }
        //Todo finish it
        annotatedFields.forEach(dtoField -> {
            Relation annotation = dtoField.getAnnotation(Relation.class);
            if (appliedOnlyTo != Void.class) {
                if (!annotation.className().equals(appliedOnlyTo)) {
                    log.warn("{} was skipped because the entity type not the same to appliedOnlyTo. Remove appliedOnlyTo parameter from @Dto", dtoField.getName());
                }
            }
            Object o = entitiesMap.get(annotation.className());
            String fieldName = annotation.fieldName();
            try {
                Field entityField = o.getClass().getDeclaredField(fieldName);
                entityField.setAccessible(true);
                dtoField.setAccessible(true);
                dtoField.set(dto, entityField.get(o));
            } catch (NoSuchFieldException e) {
                log.warn("Field {} not found.", fieldName);
            } catch (IllegalAccessException e) {
                log.warn("Access exception. {}", e.getMessage());
            }
        });
        return dto;
    }

    private static Map<Class<?>, Object> prepareEntitiesMap(Object... entities) {
        if (entities == null
                || Arrays.stream(entities).map(Object::getClass).distinct().toArray().length != entities.length) {
            return null;
        }
        Map<Class<?>, Object> entitiesMap = new HashMap<>();
        Arrays.stream(entities).forEach(o -> entitiesMap.put(o.getClass(), o));
        return entitiesMap;
    }

}
