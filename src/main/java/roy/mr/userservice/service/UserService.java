package roy.mr.userservice.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import roy.mr.userservice.error.DataNotFoundException;
import roy.mr.userservice.model.User;
import roy.mr.userservice.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    @Cacheable(value = "user", key = "#id")
    public User read(Long id) throws DataNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("user not found with id " + id));
    }

    @CachePut(cacheNames = "user", key = "#user.id")
    public User update(User user) {
        return userRepository.save(user);
    }

    @CacheEvict(cacheNames = "user", key = "#id", beforeInvocation = true)
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
