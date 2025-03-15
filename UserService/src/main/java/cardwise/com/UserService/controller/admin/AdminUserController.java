package cardwise.com.UserService.controller.admin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cardwise.com.UserService.error.exception.RoleNotFoundException;
import cardwise.com.UserService.error.exception.UserNotFoundException;
import cardwise.com.UserService.service.admin.AdminUserService;
import cardwise.com.UserService.settings.ApiPath;

@RestController
@RequestMapping(ApiPath.ADMIN_USER_CONTROLLER_PATH)
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Tag(
        name = "Admin User Controller",
        description = "This stuff controller allows create admins, ban, unban etc. ONLY for admins"
)
@SecurityRequirement(name = "JWT")
public class AdminUserController {
    AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PutMapping("make_admin/{id}")
    public ResponseEntity<Void> makeAdmin(@PathVariable long id) throws RoleNotFoundException, UserNotFoundException {
        adminUserService.makeUserAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("ban/{id}")
    public ResponseEntity<Void> banUser(@PathVariable long id) throws UserNotFoundException {
        adminUserService.banUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("unban/{id}")
    public ResponseEntity<Void> unbanUser(@PathVariable long id) throws UserNotFoundException {
        adminUserService.unbanUser(id);
        return ResponseEntity.noContent().build();
    }
}
