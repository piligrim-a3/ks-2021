package ru.bgpu.lk.annotation;

public class DataExample {

    @InjectProperty(defaultValue = "example")
    public static String name;

    @InjectProperty(name = "surname", defaultValue = "example")
    public static String name2;

    @InjectProperty(name = "global-count", defaultValue = "0")
    public static Integer count;

    @InjectProperty(defaultValue = "false,false,true")
    public static boolean[] listOfBooleans;

    @InjectProperty(defaultValue = "1,2,3,4")
    public static int[] listOfIntegers;

    @InjectProperty(defaultValue = "a,b,c")
    public static char[] listOfCharacters;
}
