package com.example.springControllerReturnOptions.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Normal Model and View Controller example
 */
@Controller
public class HtmlController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/home/mav")
    public ModelAndView homeMav(ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
