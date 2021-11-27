package ru.bgpu.lk.annotation;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
            if (!new File("config.properties").exists()) {
                createDefaultFile();
            }

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
                    } else if (field.getType().getComponentType().equals(Boolean.TYPE)) {
                        String[] values = strValue.split(",");
                        boolean[] array = new boolean[values.length];

                        for (int i = 0; i < values.length; i++) {
                            array[i] = Boolean.parseBoolean(values[i]);
                        }

                        field.set(null, array);
                    } else if (field.getType().getComponentType().equals(Integer.TYPE)) {
                        String[] values = strValue.split(",");
                        int[] array = new int[values.length];

                        for (int i = 0; i < values.length; i++) {
                            array[i] = Integer.parseInt(values[i]);
                        }

                        field.set(null, array);
                    } else if (field.getType().getComponentType().equals(Character.TYPE)) {
                        String[] values = strValue.split(",");
                        char[] array = new char[values.length];

                        for (int i = 0; i < values.length; i++) {
                            array[i] = values[i].charAt(0);
                        }

                        field.set(null, array);
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

    private static void createDefaultFile() throws IOException {
        FileOutputStream stream = new FileOutputStream("config.properties");
        Properties properties = new Properties();

        Reflections ref = new Reflections("ru.bgpu.lk.annotation", FieldsAnnotated);
        for (Field field : ref.getFieldsAnnotatedWith(InjectProperty.class)) {
            InjectProperty findable = field.getAnnotation(InjectProperty.class);
            properties.setProperty(
                    findable.name().isEmpty() ? field.getName() : findable.name(),
                    findable.defaultValue()
            );
        }

        properties.store(stream, null);
    }
}
