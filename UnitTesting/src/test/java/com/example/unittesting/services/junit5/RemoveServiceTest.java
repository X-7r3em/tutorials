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
                        .body(mapper.writeValueAsString(new Echo("Echo Test Message"))));

        String message = remoteService.postEchoMessage("Test Message");
        assertEquals("Echo Test Message", message);
        mockServer.verify();
    }
    
    /**
     * Example for expecting a request that is not made or making a request that is not expected will fail the verify.
     * If the expected routes are called in the same order, the mockServer will be valid.
     * If the expected routes are not called in the same order, the mockServer will not be valid.
     * If there is a call on a unexpected route, then the mockServer will not be valid.
     * If there is no call on an expected route, then the mockServer will not be valid.
     *
     * This can be tested by commenting the mock or the act part.
     */
    @Test
    public void multipleCallsToDifferentRoutes() throws JsonProcessingException {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(ExpectedCount.once(),
                requestTo("/echo/?message=Test"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("Echo Test"));

        mockServer.expect(ExpectedCount.once(),
                requestTo("/echo/"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header("test", "This is a test header"))
                .andExpect(content().string(mapper.writeValueAsString(new Echo("Test Message"))))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(new Echo("Echo Test Message"))));

        String getMessage = remoteService.getEchoMessage("Test");
        String postEchoMessage = remoteService.postEchoMessage("Test Message");
        assertEquals("Echo Test", getMessage);
        assertEquals("Echo Test Message", postEchoMessage);
        mockServer.verify();
    }
}
