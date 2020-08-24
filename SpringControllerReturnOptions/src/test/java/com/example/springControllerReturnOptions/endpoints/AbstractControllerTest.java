package com.example.springControllerReturnOptions.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class AbstractControllerTest {
    @Autowired
    protected MockMvc mockMvc;
}
