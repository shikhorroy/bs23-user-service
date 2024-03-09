package roy.mr.userservice.error;


import lombok.Getter;

@Getter
public class DataNotFoundException extends Exception {
    private String message;

    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
