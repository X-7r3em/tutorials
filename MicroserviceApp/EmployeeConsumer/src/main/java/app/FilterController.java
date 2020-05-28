package app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FilterController {

    @GetMapping("/")
    public String getFilter(HttpServletRequest request) {

        Object valueObject = request.getHeader("pre");
        String value = valueObject.toString();
        System.out.println(value);

        return value;
    }

}
