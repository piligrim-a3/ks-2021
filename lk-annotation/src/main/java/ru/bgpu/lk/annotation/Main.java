package ru.bgpu.lk.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationScanner.scan();
        logger.info("check DataExample: {} - {}",DataExample.name, DataExample.count);
    }
}
