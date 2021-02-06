package com.example.springControllerReturnOptions.endpoints;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResponseEntityController.class)
class ResponseEntityControllerTest extends AbstractControllerTest {

    @Test
    public void text_givenNormalRequest_willReturnText() throws Exception {
        mockMvc.perform(get("/text"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string("This is plain text"));
    }

    @Test
    public void html_givenNormalRequest_willReturnHtml() throws Exception {
        mockMvc.perform(get("/html"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(content().string("<h1>This is HTML<h1>"));
    }

    @Test
    public void xml_givenNormalRequest_willReturnXML() throws Exception {
        String expectedXML =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + System.lineSeparator() +
                        "<note>" + System.lineSeparator() +
                        "    <body>This is XML body</body>" + System.lineSeparator() +
                        "</note>";

        mockMvc.perform(get("/xml"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(content().string(expectedXML));
    }

    @Test
    public void json_givenNormalRequest_willReturnJSON() throws Exception {
        String expectedJSON = "{\"Key\":\"This is JSON\"}";
        mockMvc.perform(get("/json"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expectedJSON));
    }

}