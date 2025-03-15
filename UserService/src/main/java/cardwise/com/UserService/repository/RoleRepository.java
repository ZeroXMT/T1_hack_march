package cardwise.com.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cardwise.com.UserService.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getByTitle(String title);
}
