package roy.mr.userservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import roy.mr.userservice.model.Response;

public class ResponseUtil {
    private ResponseUtil() {
    }

    public static ResponseEntity<Response> success(Object data) {
        return ResponseEntity.ok(new Response(true, "success", data));
    }

    public static ResponseEntity<Response> success(String message, Object data) {
        return ResponseEntity.ok(new Response(true, message, data));
    }

    public static ResponseEntity<Response> error(String message) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Response(false, message, null));
    }

    public static ResponseEntity<Response> error(String message, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(new Response(false, message, null));
    }
}
