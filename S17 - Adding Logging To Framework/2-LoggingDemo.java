package com.letskodeit.overview;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingDemo {

    private static final Logger log = LogManager.getLogger(LoggingDemo.class.getName());

    public static void main(String[] args) {
        log.trace("Trace Message Printed");
        log.debug("Debug Message Printed");
        log.info("Info Message Printed");
        log.error("Error Message Printed");
        log.fatal("Fatal Message Printed");
    }
}
