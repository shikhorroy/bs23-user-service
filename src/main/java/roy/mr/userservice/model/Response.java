package roy.mr.userservice.model;

public record Response(boolean isSuccess, String message, Object data) {
}
