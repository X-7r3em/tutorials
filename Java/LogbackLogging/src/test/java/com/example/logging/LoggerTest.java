package com.example.logging;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoggerTest {
    /**
     * The logger needs to be static as we only need one instance for all objects and instantiation a new one
     * would be a waste
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void logging() {
        LOGGER.debug("Debug");
        LOGGER.info("Info");
        LOGGER.warn("Warn");
        LOGGER.error("Error");
    }

    /**
     * Loggers have inheritance similar to packages. The testLogger has a name of com.example.logging.test.
     * It catches the appender with the same name. However, it is a child of the root logger as well
     * and it will log to it also.
     */
    @Test
    public void inheritedLoggers() {
        Logger testLogger = LoggerFactory.getLogger("com.example.logging.test");
        LOGGER.debug("Debug");
        LOGGER.info("Info");
        LOGGER.warn("Warn");
        LOGGER.error("Error");

        testLogger.debug("Debug");
        testLogger.info("Info");
        testLogger.warn("Warn");
        testLogger.error("Error");
    }

    @Test
    public void loggingExceptionsDoesNotRequireBrackets() {
        try {
            throw new IllegalArgumentException();
        } catch (Exception e) {
            LOGGER.info("Exception was thrown", e);
        }
    }

}
