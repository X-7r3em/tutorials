package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    private static int counter = 0;

    @GetMapping("/")
    public String getHello() {

        LOGGER.info("I WAS LOGGED: {}", counter++);
        LOGGER.debug("I WAS DEBUGGED: {}", counter++);

        return "Hello there";
    }
}
