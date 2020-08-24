package com.example.springControllerReturnOptions.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestExampleController.class)
class RestExampleControllerTest extends AbstractControllerTest {

    @Test
    public void json_givenNormalRequest_willReturnJSON() throws Exception {
        String expectedJSON = "{\"List\":[\"This is my rest response\"]}";
        mockMvc.perform(get("/rest/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expectedJSON));
    }

    @Test
    public void text_givenNormalRequest_willReturnText() throws Exception {
        String expectedText = "This is my rest text response";
        mockMvc.perform(get("/rest/text"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                .andExpect(content().string(expectedText));
    }
}