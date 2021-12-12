package ru.bgpu.task.annotation;

import ru.bgpu.task.annotation.annotations.InjectProperty;
import ru.bgpu.task.annotation.example.ClassWithAnnotatedFields;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnnotationPropertyInjector {

    private static final String PROPERTY_PATH = "tasks/task-annotation/src/main/resources/config.properties";

    public static void inject() throws Exception {
        Properties props = load(PROPERTY_PATH);
        Class<?> clazz = ClassWithAnnotatedFields.class;
        Object object = clazz.newInstance();
        for (Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(InjectProperty.class)) {
                String parameter = field.getAnnotation(InjectProperty.class).name();
                if (Collection.class.isAssignableFrom(field.getType())) {
                    if(!parameter.isEmpty()) {
                        switch (getTypeOfGeneric(field.getGenericType().getTypeName())) {
                            case "Integer":
                                Collection<Integer> integerCollection = (Collection<Integer>) field.getType().newInstance();
                                String[] intArray = props.getProperty(parameter).split(", ");
                                for (String s : intArray) {
                                    integerCollection.add(Integer.parseInt(s));
                                }
                                field.set(object, integerCollection);
                                break;
                            case "Double":
                                Collection<Double> doubleCollection = (Collection<Double>) field.getType().newInstance();
                                String[] doubleArray = props.getProperty(parameter).split(", ");
                                for (String s : doubleArray) {
                                    doubleCollection.add(Double.parseDouble(s));
                                }
                                field.set(object, doubleCollection);
                                break;
                            case "String":
                                Collection<String> stringCollection = (Collection<String>) field.getType().newInstance();
                                String[] array = props.getProperty(parameter).split(", ");
                                stringCollection.addAll(Arrays.asList(array));
                                field.set(object, stringCollection);
                                break;
                        }
                    } else {
                        switch (getTypeOfGeneric(field.getGenericType().getTypeName())) {
                            case "Integer":
                                Collection<Integer> integerCollection = (Collection<Integer>) field.getType().newInstance();
                                String[] intArray = props.getProperty(field.getName()).split(", ");
                                for (String s : intArray) {
                                    integerCollection.add(Integer.parseInt(s));
                                }
                                field.set(object, integerCollection);
                                break;
                            case "Double":
                                Collection<Double> doubleCollection = (Collection<Double>) field.getType().newInstance();
                                String[] doubleArray = props.getProperty(field.getName()).split(", ");
                                for (String s : doubleArray) {
                                    doubleCollection.add(Double.parseDouble(s));
                                }
                                field.set(object, doubleCollection);
                                break;
                            case "String":
                                Collection<String> stringCollection = (Collection<String>) field.getType().newInstance();
                                String[] array = props.getProperty(field.getName()).split(", ");
                                stringCollection.addAll(Arrays.asList(array));
                                field.set(object, stringCollection);
                                break;
                        }
                    }
                } else {
                    if (!parameter.isEmpty()) {
                        switch (typeCheck(field.getType())) {
                            case "Integer": field.set(object, Integer.parseInt(props.getProperty(parameter)));break;
                            case "Double": field.set(object, Double.parseDouble(props.getProperty(parameter)));break;
                            case "String": field.set(object, props.getProperty(parameter));break;
                        }
                    } else {
                        switch (typeCheck(field.getType())) {
                            case "Integer": field.set(object, Integer.parseInt(props.getProperty(field.getName())));break;
                            case "Double": field.set(object, Double.parseDouble(props.getProperty(field.getName())));break;
                            case "String": field.set(object, props.getProperty(field.getName()));break;
                        }
                    }
                }
            }
        }
        System.out.println("Injected params:");
        for (Field field : object.getClass().getFields()) {
            System.out.println(field.get(object));
        }
    }

    private static Properties load(String path) {
        File config = new File(path);
        Properties props = new Properties();
        try (FileReader reader = new FileReader(config)) {
            props.load(reader);
        } catch (FileNotFoundException exception) {
            System.out.println("Properties not found");
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return props;
    }

    private static String typeCheck(Class<?> clazzType) {
        if (clazzType.equals(Integer.class)) {
            return "Integer";
        }
        if (clazzType.equals(Double.class)) {
            return "Double";
        }
        return "String";
    }

    private static String getTypeOfGeneric(String fullName) {
        String genericType = "";
        Pattern pattern = Pattern.compile(".*\\.(.*)>");
        Matcher matcher = pattern.matcher(fullName);
        while (matcher.find()) {
            genericType = matcher.group(1);
        }
        return genericType;
    }

}
