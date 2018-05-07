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
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class DtoUtils {

    public static <T> T convertToDto(Class<T> dtoType, Object... entities) {
        Map<Class<?>, Object> entitiesMap = prepareEntitiesMap(entities);
        if (entitiesMap == null) {
            log.warn("DTO {}.java wasn't created because entities list isn't unique or it's NULL.",
                    dtoType.getSimpleName());
            return null;
        }

        T dto = InstanceUtils.newInstance(dtoType);

        getAnnotatedFields(dtoType).forEach(dtoField -> {
            Relation annotation = dtoField.getAnnotation(Relation.class);
            Class<?> appliedTo = annotation.className();
            Class<?> appliedOnlyTo = getAppliedOnlyToParam(dtoType);
            if (appliedOnlyTo != Void.class) {
                if (appliedTo == Void.class) {
                    appliedTo = appliedOnlyTo;
                } else if (!appliedTo.equals(appliedOnlyTo)) {
                    log.warn("{} was skipped because the entity type not the same to declared appliedOnlyTo parameter. Remove appliedOnlyTo parameter from @Dto", dtoField.getName());
                    appliedTo = Void.class;
                }
            }
            Object o = entitiesMap.get(appliedTo);
            if (o == null) {
                log.warn("No present entity with annotated type {}.", appliedTo.getSimpleName());
            } else {
                String annotatedFieldName = annotation.fieldName();
                String fieldName = !annotatedFieldName.isEmpty() ? annotatedFieldName : dtoField.getName();
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
            }
        });
        return dto;
    }

    private static <T> List<Field> getAnnotatedFields(Class<T> dtoType) {
        return Arrays.stream(dtoType.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Relation.class))
                .collect(Collectors.toList());
    }

    private static Map<Class<?>, Object> prepareEntitiesMap(Object... entities) {
        if (entities == null
                || Arrays.stream(entities).map(Object::getClass).distinct().count() != entities.length) {
            return null;
        }
        return Arrays.stream(entities).collect(Collectors.toMap(Object::getClass, Function.identity()));
    }

    private static Class<?> getAppliedOnlyToParam(Class<?> dtoType) {
        Dto dtoTypeAnnotation = dtoType.getAnnotation(Dto.class);
        if (dtoTypeAnnotation != null) {
            return dtoTypeAnnotation.appliedOnlyTo();
        } else {
            return Void.class;
        }
    }

}
