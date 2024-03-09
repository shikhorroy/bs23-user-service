package roy.mr.userservice.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import roy.mr.userservice.model.Response;
import roy.mr.userservice.util.ResponseUtil;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {DataNotFoundException.class})
    protected ResponseEntity<Response> handleDataNotFound(DataNotFoundException ex, WebRequest request) {
        return ResponseUtil.error(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Response> handleAllError(Exception ex, WebRequest request) {
        return ResponseUtil.error(ex.getMessage());
    }
}
