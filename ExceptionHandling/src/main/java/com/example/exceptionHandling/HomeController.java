package com.example.exceptionHandling;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "Hello";
    }

    @GetMapping("/cus")
    public String getCustomException() {
        throw new CustomException("This is my Custom Exception.");
    }


    @GetMapping("/ex")
    public String getException() throws Exception {
        throw new Exception("This is Local Checked Exception.");
    }

}
