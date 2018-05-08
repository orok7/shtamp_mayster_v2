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
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class DtoUtils {

    private static final Class<?> DEFAULT_VALUE_APPLIED_TO = Void.class;

    public static <T> T convertToDto(Class<T> dtoType, Object... entities) {
        Map<Class<?>, Object> entitiesMap = prepareEntitiesMap(entities);
        if (entitiesMap == null) {
            log.warn("DTO {}.java wasn't created because entities list isn't unique or it's NULL.",
                    dtoType.getSimpleName());
            return null;
        }
        T dto = InstanceUtils.newInstance(dtoType);
        getAllFields(dtoType).forEach(fieldInitialization(dtoType, entitiesMap, dto));
        return dto;
    }

    public static <T> T convertToEntity(Class<T> entityType, Object dto) {
        T entity = InstanceUtils.newInstance(entityType);
        if (entity == null) {
            return null;
        }
        getFieldsByEntityType(entityType, dto).forEach(dtoField -> setEntityField(entity, dtoField, dto));
        return entity;
    }

    private static <T> Consumer<Field> fieldInitialization(Class<T> dtoType, Map<Class<?>, Object> entitiesMap, T dto) {
        return dtoField -> {
            Class<?> appliedTo = getAppliedTo(dtoType, dtoField);
            if (appliedTo.equals(DEFAULT_VALUE_APPLIED_TO)) {
                log.warn("Field {} was skipped. @Relation.appliedTo needed for initialization.", dtoField.getName());
                return;
            }
            Object entity = entitiesMap.get(appliedTo);
            if (entity == null) {
                log.warn("No present entity with annotated type {}.", appliedTo.getSimpleName());
            } else {
                setDtoField(dto, dtoField, entity);
            }
        };
    }

    private static void setDtoField(Object dest, Field dtoField, Object source) {
        String foundFieldName = getFoundFieldName(dtoField);
        try {
            Field foudField = source.getClass().getDeclaredField(foundFieldName);
            setField(dest, dtoField, source, foudField);
        } catch (NoSuchFieldException e) {
            log.warn("Field '{}' not found in {}.", foundFieldName, source.getClass().getName());
        } catch (IllegalAccessException e) {
            log.warn("Access exception. {}", e.getMessage());
        }
    }

    private static void setEntityField(Object dest, Field dtoField, Object source) {
        String foundFieldName = getFoundFieldName(dtoField);
        try {
            Field foundField = dest.getClass().getDeclaredField(foundFieldName);
            setField(dest, foundField, source, dtoField);
        } catch (NoSuchFieldException e) {
            log.warn("Field '{}' not found in {}.", foundFieldName, source.getClass().getName());
        } catch (IllegalAccessException e) {
            log.warn("Access exception. {}", e.getMessage());
        }
    }

    private static void setField(Object dest, Field destField, Object source, Field sourceField)
            throws IllegalAccessException {
        sourceField.setAccessible(true);
        destField.setAccessible(true);
        destField.set(dest, sourceField.get(source));
    }

    private static String getFoundFieldName(Field dtoField) {
        Relation annotation = dtoField.getAnnotation(Relation.class);
        String fieldName = annotation != null ? annotation.fieldName() : "";
        return !fieldName.isEmpty() ? fieldName : dtoField.getName();
    }

    private static Class<?> getAppliedTo(Class<?> dtoType, Field dtoField) {
        Class<?> appliedToField = getAppliedToField(dtoField);
        Class<?> appliedToClass = getAppliedToClass(dtoType);
        if (appliedToField.equals(DEFAULT_VALUE_APPLIED_TO)) {
            return appliedToClass;
        }
        return appliedToField;
    }

    private static Class<?> getAppliedToField(Field dtoField) {
        return dtoField.isAnnotationPresent(Relation.class) ? dtoField.getAnnotation(Relation.class).appliedTo() : DEFAULT_VALUE_APPLIED_TO;
    }

    private static Class<?> getAppliedToClass(Class<?> dtoType) {
        return dtoType.isAnnotationPresent(Dto.class) ? dtoType.getAnnotation(Dto.class).appliedTo() : DEFAULT_VALUE_APPLIED_TO;
    }

    private static List<Field> getAllFields(Class<?> dtoType) {
        return Arrays.stream(dtoType.getDeclaredFields())
                .collect(Collectors.toList());
    }

    private static Map<Class<?>, Object> prepareEntitiesMap(Object... entities) {
        if (entities == null
                || Arrays.stream(entities).map(Object::getClass).distinct().count() != entities.length) {
            return null;
        }
        return Arrays.stream(entities).collect(Collectors.toMap(Object::getClass, Function.identity()));
    }

    private static List<Field> getFieldsByEntityType(Class<?> entityType, Object dto) {
        return Arrays.stream(dto.getClass().getDeclaredFields())
                .filter(getFieldByEntityTypePredicate(entityType))
                .collect(Collectors.toList());
    }

    private static Predicate<Field> getFieldByEntityTypePredicate(Class<?> entityType) {
        return dtoField -> getAppliedToField(dtoField).equals(entityType) || getAppliedToClass(dtoField.getDeclaringClass())
                .equals(entityType);
    }

}
