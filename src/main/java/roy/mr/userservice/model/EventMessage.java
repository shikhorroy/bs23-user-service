package roy.mr.userservice.model;

import lombok.Getter;
import roy.mr.userservice.model.enums.EventType;

import java.io.Serializable;

@Getter
public class EventMessage implements Serializable {
    private final EventType eventType;
    private final String message;

    public EventMessage(EventType eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    @Override
    public String toString() {
        return "EventMessage{eventType=" + eventType + ", message=" + message + "}";
    }
}
