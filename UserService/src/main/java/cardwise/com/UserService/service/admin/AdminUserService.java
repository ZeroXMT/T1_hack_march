package cardwise.com.UserService.service.admin;

import org.springframework.stereotype.Service;
import cardwise.com.UserService.error.exception.RoleNotFoundException;
import cardwise.com.UserService.error.exception.UserNotFoundException;
import cardwise.com.UserService.model.LUser;
import cardwise.com.UserService.model.Role;
import cardwise.com.UserService.repository.LUserRepository;
import cardwise.com.UserService.repository.RoleRepository;

@Service
public class AdminUserService {
    LUserRepository lUserRepository;
    RoleRepository roleRepository;
    final String roleAdminTitle = "ROLE_ADMIN";

    public AdminUserService(LUserRepository lUserRepository, RoleRepository roleRepository) {
        this.lUserRepository = lUserRepository;
        this.roleRepository = roleRepository;
    }

    public void makeUserAdmin(long userId) throws RoleNotFoundException, UserNotFoundException {
        final LUser user = findUser(userId);
        Role adminRole = roleRepository.getByTitle(roleAdminTitle).orElseThrow(
                () -> new RoleNotFoundException("No role with title ROLE_ADMIN")
        );
        if (!user.getRoles().contains(adminRole)) {
            user.getRoles().add(adminRole);
        }
    }

    public void banUser(long userId) throws UserNotFoundException {
        final LUser user = findUser(userId);
        user.setEnabled(false);
    }

    public void unbanUser(long userId) throws UserNotFoundException {
        final LUser user = findUser(userId);
        user.setEnabled(true);
    }

    private LUser findUser(long userId) throws UserNotFoundException {
         return lUserRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("No user with that id. Given id: " + userId)
        );
    }

}
