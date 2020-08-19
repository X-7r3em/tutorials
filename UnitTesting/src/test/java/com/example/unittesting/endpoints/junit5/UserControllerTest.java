package com.example.unittesting.endpoints.junit5;

import com.example.unittesting.endpoints.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.unittesting.dtos.User;
import com.example.unittesting.services.UserService;
import com.example.unittesting.services.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Gives me the MVC testing capabilities
@WebMvcTest(UserController.class)
public class UserControllerTest {

    // Gives me the MVC object
    @Autowired
    private MockMvc mockMvc;

    // I need to mock all the dependencies of the said controller
    @MockBean
    private UserService userService;

    @Test
    public void addUser_givenUser_willCreateUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("Vasko", 15);
        String requestContent = mapper.writeValueAsString(user);
        String responseContent = mapper.writeValueAsString(user);

        given(userService.addUser(user))
                .willReturn(user);

        mockMvc.perform(post("/add")
                .header("mock-header", "mock-value")
                .contentType(APPLICATION_JSON)
                .content(requestContent))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().string(responseContent));

        // This verifies that the userService was called with the proper method the needed
        // amount of times and the arguments were equal.
        then(userService)
                .should()
                .addUser(user);
    }

    @Test
    public void addUser_givenInvalidUser_willThrowException() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("Vasko", 15);
        String requestContent = mapper.writeValueAsString(user);

        willThrow(UserNotFoundException.class)
                .given(userService)
                .addUser(user);

        mockMvc.perform(post("/add")
                .header("mock-header", "mock-value")
                .contentType(APPLICATION_JSON)
                .content(requestContent))
                .andExpect(status().isOk());

        // This verifies that the userService was called with the proper method the needed
        // amount of times and the arguments were equal.
        then(userService)
                .should()
                .addUser(user);
    }
}