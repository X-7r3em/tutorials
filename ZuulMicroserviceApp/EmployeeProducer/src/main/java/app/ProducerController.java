package app;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProducerController {

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee getEmployee(HttpServletRequest request) {
        Employee emp = new Employee();
        emp.setName("emp1");
        emp.setDesignation("manager");
        emp.setEmpId("1");
        emp.setSalary(3000);
        return emp;
    }

    @RequestMapping(value = "/exc", method = RequestMethod.GET)
    public Employee throwException(HttpServletRequest request) {
        throw new RuntimeException();
    }

    /**
     * The Proxy can return a proper error json response
     *
     * @param exception - the caught exception
     * @return - the error response
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleError(RuntimeException exception) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, "This is api error", "this is the error");
        return new ResponseEntity<>(apiError, headers, HttpStatus.CONFLICT);
    }

}