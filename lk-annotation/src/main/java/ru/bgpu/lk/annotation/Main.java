package ru.bgpu.lk.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationScanner.scan();
        logger.info("check DataExample: {} - {}", DataExample.name, DataExample.count);
        logger.info("check boolean array in DataExample: {}", DataExample.listOfBooleans);
        logger.info("check integer array in DataExample: {}", DataExample.listOfIntegers);
        logger.info("check character array in DataExample: {}", DataExample.listOfCharacters);
    }
}
