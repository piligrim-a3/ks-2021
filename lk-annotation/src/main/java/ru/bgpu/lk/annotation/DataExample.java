package ru.bgpu.lk.annotation;

public class DataExample {

    @InjectProperty(defaultValue = "example")
    public static String name;

    @InjectProperty(name = "surname", defaultValue = "example")
    public static String name2;

    @InjectProperty(name = "global-count", defaultValue = "0")
    public static Integer count;
}
