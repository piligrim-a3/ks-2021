package ru.bgpu.task.annotation;

public class Main {

    public static void main(String[] args) throws Exception{
        AnnotationScanner.scan();
        AnnotationPropertyInjector.inject();
    }
}
