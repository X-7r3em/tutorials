package com.example.springControllerReturnOptions.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class AbstractControllerTest {
    protected static final String HOST = "http://localhost:8080/";

    @Autowired
    protected MockMvc mockMvc;
}
