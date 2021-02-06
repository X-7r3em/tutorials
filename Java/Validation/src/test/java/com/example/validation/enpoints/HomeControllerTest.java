package com.example.validation.enpoints;

import com.example.validation.dto.Bear;
import com.example.validation.dto.Car;
import com.example.validation.endpoints.HomeController;
import com.example.validation.services.DemoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private DemoService demoService;

    @Test
    public void createBear_givenInvalidBear_willReturnBadResponse() throws Exception {
        Bear mockBear = new Bear();

        mockMvc.perform(post("/bear")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockBear)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createBear_givenBear_willReturnBear() throws Exception {
        Bear mockBear = new Bear("NameIsNam", 15);

        mockMvc.perform(post("/bear")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockBear)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(mockBear)));
    }

    @Test
    public void throwException_given_willThrowException() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/ex")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertEquals("Some exception message", contentAsString);
    }

    @Test
    public void throwCustomException_given_willThrowException() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/custom")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertEquals("Some custom exception message", contentAsString);
    }

    @Test
    public void customMethodAnnotation_givenFirstNameAndLastName_willReturnAdminName() throws Exception {
        mockMvc.perform(get("/admin")
                .param("firstName", "Vasil")
                .param("lastName", "Egov"))
                .andExpect(status().isOk())
                .andExpect(content().string("Vasil Egov"));
    }

    @Test
    public void customMethodAnnotation_givenInvalidFirstNameAndInvalidLastName_willReturnBadResponse() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin")
                .param("firstName", "Peter")
                .param("lastName", "Gigov"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertEquals("customMethodAnnotation.<cross-parameter>: The name is not the one of the admin", contentAsString);
    }

    @Test
    public void methodCustomException_givenFirstNameAndLastName_willReturnAdminName() throws Exception {
        mockMvc.perform(get("/validated")
                .param("firstName", "Vasil")
                .param("lastName", "Egov"))
                .andExpect(status().isOk())
                .andExpect(content().string("Vasil Egov"));
    }

    @Test
    public void methodCustomException_givenInvalidFirstNameAndInvalidLastName_willReturnBadResponse() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/validated")
                .param("firstName", "Peter")
                .param("lastName", "Gigov"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertEquals(
                "methodWithValidatedAndCustomAnnotation.<cross-parameter>: The name is not the one of the admin",
                contentAsString);
    }

    @Test
    public void methodWithValid_givenMake_willReturnAdminName() throws Exception {
        Car car = new Car("Ferrari");
        mockMvc.perform(get("/valid")
                .param("make", "Ferrari"))
                .andExpect(status().isOk())
                .andExpect(content().string(car.toString()));
    }

    @Test
    public void methodWithValid_givenInvalidMake_willReturnBadResponse() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/valid"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        List<String> violations = Arrays.stream(contentAsString.split(", "))
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());

        assertEquals("methodWithValid.car.make: must not be null", violations.get(0));
        assertEquals("methodWithValid.make: Make is null. But why?", violations.get(1));
    }

}
