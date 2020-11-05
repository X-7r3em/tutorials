package com.example.unittesting.services.junit5;

import com.example.unittesting.dtos.Echo;
import com.example.unittesting.services.RemoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@SpringBootTest
public class RemoveServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveServiceTest.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RemoteService remoteService;

    @Autowired
    private ObjectMapper mapper;


    /**
     * Example for mocking a remote http server for a REST GET call.
     */
    @Test
    public void getEchoMessage_givenMessage_willReturnEchoMessage() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(ExpectedCount.once(),
                requestTo("/echo/?message=Test%20Message"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withStatus(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body("Echo Test Message")
                );

        String message = remoteService.getEchoMessage("Test Message");
        assertEquals("Echo Test Message", message);
        mockServer.verify();
    }

    /**
     * Example for mocking a remote http server for a REST POST call.
     */
    @Test
    public void postEchoMessage_givenMessage_willReturnEchoMessage() throws JsonProcessingException {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(ExpectedCount.once(),
                requestTo("/echo/"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header("test", "This is a test header"))
                .andExpect(content().string(mapper.writeValueAsString(new Echo("Test Message"))))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(new Echo("Echo Test Message")))
                );

        String message = remoteService.postEchoMessage("Test Message");
        assertEquals("Echo Test Message", message);
        mockServer.verify();
    }
}
