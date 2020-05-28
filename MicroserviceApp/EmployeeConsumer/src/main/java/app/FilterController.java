package app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RestController
public class FilterController {

    @GetMapping("/")
    public String getFilter(HttpServletRequest request, HttpServletResponse response) {

        Object valueObject = request.getHeader("pre");
        String value = valueObject.toString();

        return value;
    }

}
