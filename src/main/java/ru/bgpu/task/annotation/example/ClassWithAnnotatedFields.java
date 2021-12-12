package ru.bgpu.task.annotation.example;

import ru.bgpu.task.annotation.annotations.InjectProperty;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ClassWithAnnotatedFields {

    @InjectProperty(name = "loadName")
    public static String simpleName;

    @InjectProperty
    public static Integer size;

    @InjectProperty(name = "int")
    public static Integer intField;

    @InjectProperty
    public static Double aDouble;

    @InjectProperty(name = "stringsAnnotation")
    public static ArrayList<String> strings;

    @InjectProperty
    public static LinkedList<Double> doubles;

    @InjectProperty(name = "integersAnnotation")
    public static PriorityQueue<Integer> integers;
}
