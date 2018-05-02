package eins.utils.dto;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Relation {
    Class<?> className();
    String fieldName();
}
