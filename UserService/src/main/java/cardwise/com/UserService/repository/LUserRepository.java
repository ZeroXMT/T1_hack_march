package cardwise.com.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cardwise.com.UserService.model.LUser;

import java.util.Optional;

public interface LUserRepository extends JpaRepository<LUser, Long> {
    Optional<LUser> findByEmail(String name);
}
