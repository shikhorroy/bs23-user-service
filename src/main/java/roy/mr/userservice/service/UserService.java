package roy.mr.userservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import roy.mr.userservice.error.DataNotFoundException;
import roy.mr.userservice.model.EventMessage;
import roy.mr.userservice.model.User;
import roy.mr.userservice.model.enums.EventType;
import roy.mr.userservice.repository.UserRepository;
import roy.mr.userservice.util.KafkaUtil;

@Service
public class UserService {

    @Value("${spring.kafka.user-topic}")
    private String topic;
    private final KafkaUtil kafkaUtil;
    private final UserRepository userRepository;

    public UserService(KafkaUtil kafkaUtil, UserRepository userRepository) {
        this.kafkaUtil = kafkaUtil;
        this.userRepository = userRepository;
    }

    public User create(User user) {
        User savedUser = userRepository.save(user);
        kafkaUtil.sendMessage(topic, new EventMessage(EventType.INSERT, savedUser.toString()));
        return savedUser;
    }

    @Cacheable(value = "user", key = "#id")
    public User read(Long id) throws DataNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("user not found with id " + id));
    }

    @CachePut(cacheNames = "user", key = "#user.id")
    public User update(User user) {
        User updatedUser = userRepository.save(user);
        kafkaUtil.sendMessage(topic, new EventMessage(EventType.UPDATE, updatedUser.toString()));
        return updatedUser;
    }

    @CacheEvict(cacheNames = "user", key = "#id", beforeInvocation = true)
    public void delete(Long id) throws DataNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("user not found with id " + id));
        userRepository.deleteById(id);

        kafkaUtil.sendMessage(topic, new EventMessage(EventType.DELETE, user.toString()));
    }
}
