package org.example.unittestexample.endpoints;

import org.example.unittestexample.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Gives me the MVC testing capabilities
@WebMvcTest(UserController.class)
// This initializes all mocks
public class UserControllerJUnit5Test {

    //Gives me the MVC object
    @Autowired
    private MockMvc mockMvc;

    //I need to mock all the dependencies of the said controller
    @MockBean
    private UserService userService;

    @Test
    public void givenUser_whenCreateUser_shouldCreateUser() throws Exception {
        mockMvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"Vasko\",\n" +
                        "    \"age\": 15\n" +
                        "}"))
                .andExpect(status().isOk());
    }
}