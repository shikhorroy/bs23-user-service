package roy.mr.userservice.util;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import roy.mr.userservice.model.EventMessage;

@Component
public class KafkaUtil {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaUtil(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, EventMessage message) {
        kafkaTemplate.send(topic, message.toString());
    }
}
