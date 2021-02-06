package app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FilterController {

    @GetMapping("/")
    public String getFilter(HttpServletRequest request, HttpServletResponse response) {

        Object valueObject = request.getHeader("PRE");
        String value = valueObject.toString();

        response.addHeader("PRE-FROM-POST",
                "Information from PRE filter added from HttpServletResponse");

        return value;
    }

}
