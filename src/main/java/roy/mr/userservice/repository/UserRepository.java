package roy.mr.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roy.mr.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
