package ru.bgpu.lk.annotation;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Set;

import static org.reflections.scanners.Scanners.FieldsAnnotated;

public class AnnotationScanner {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationScanner.class);

    public static void scan() {
        logger.info("Сканируем используя Reflection API:");
        Reflections ref = new Reflections("ru.bgpu.lk.annotation", FieldsAnnotated);
        Set<Field> s = ref.getFieldsAnnotatedWith(InjectProperty.class);
        for (Field field : ref.getFieldsAnnotatedWith(InjectProperty.class)) {
            InjectProperty findable = field.getAnnotation(InjectProperty.class);
            logger.info("Найдено поле: {}, с именем в аннотации: {}",field.getName(), findable.name());
        }
    }
}
