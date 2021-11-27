package ru.bgpu.lk.annotation;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

import static org.reflections.scanners.Scanners.FieldsAnnotated;

public class AnnotationScanner {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationScanner.class);

    public static void scan() {
        logger.info("Загрузка файла конфигурации");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
            logger.info("Сканируем используя Reflection API:");
            Reflections ref = new Reflections("ru.bgpu.lk.annotation", FieldsAnnotated);
            for (Field field : ref.getFieldsAnnotatedWith(InjectProperty.class)) {
                InjectProperty findable = field.getAnnotation(InjectProperty.class);
                logger.info("Найдено поле: {}, с именем в аннотации: {}",field.getName(), findable.name());
                field.setAccessible(true);
                String strValue = properties.getProperty(
                        findable.name().isEmpty() ? field.getName() : findable.name(),
                        findable.defaultValue()
                );
                try {
                    if (field.getType() == String.class) {
                        field.set(null, strValue);
                    } else if (field.getType() == Integer.class) {
                        field.set(null, Integer.valueOf(strValue));
                    }
                } catch (IllegalAccessException e) {
                    logger.error("Ошибка установки значения поля",e);
                } catch (NumberFormatException e) {
                    logger.error("Ошибка типа поля",e);
                }
            }
        } catch (IOException e) {
            logger.error("Ошибка загрузки файла конфигурации",e);
        }
    }
}
