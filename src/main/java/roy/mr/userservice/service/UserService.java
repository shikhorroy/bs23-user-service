package roy.mr.userservice.service;

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

    public User read(Long id) throws DataNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("user not found with id " + id));
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
