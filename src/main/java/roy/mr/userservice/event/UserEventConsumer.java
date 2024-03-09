package roy.mr.userservice.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {
    @KafkaListener(topics = "${spring.kafka.user-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        System.out.println("User event message: " + message);
    }
}
