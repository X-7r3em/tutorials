package com.example.springControllerReturnOptions.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HtmlController.class)
class HtmlControllerTest extends AbstractControllerTest {

    @Test
    public void home_givenNormalRequest_willReturnHtml() throws Exception {
        String html =
                "<html>" + System.lineSeparator() +
                "<body>" + System.lineSeparator() +
                "This is the home HTML" + System.lineSeparator() +
                "</body>" + System.lineSeparator() +
                "</html>";

        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML + ";charset=UTF-8"))
                .andExpect(content().string(html));
    }

    @Test
    public void homeMav_givenNormalRequest_willReturnHtml() throws Exception {
        String html =
                "<html>" + System.lineSeparator() +
                        "<body>" + System.lineSeparator() +
                        "This is the home HTML" + System.lineSeparator() +
                        "</body>" + System.lineSeparator() +
                        "</html>";

        mockMvc.perform(get("/home/mav"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML + ";charset=UTF-8"))
                .andExpect(content().string(html));
    }
}