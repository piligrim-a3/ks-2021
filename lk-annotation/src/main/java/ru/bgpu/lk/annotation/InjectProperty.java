package ru.bgpu.lk.annotation;

import java.lang.annotation.*;

@Target(value= ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface InjectProperty {
    String name() default "";
    String defaultValue() default "";
}
