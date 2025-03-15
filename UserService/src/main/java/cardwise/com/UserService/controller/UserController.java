package cardwise.com.UserService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import cardwise.com.UserService.dto.LUserDefaultDto;
import cardwise.com.UserService.dto.LUserRegistrationDto;
import cardwise.com.UserService.error.exception.RoleNotFoundException;
import cardwise.com.UserService.error.exception.UserNotFoundException;
import cardwise.com.UserService.service.LUserService;
import cardwise.com.UserService.settings.ApiPath;
import java.util.List;

@RestController
@RequestMapping(ApiPath.USER_CONTROLLER_PATH)
@Tag(
        name = "User Controller",
        description = "allows to manage users and get users data"
)
public class UserController {
    LUserService userService;

    public UserController(LUserService userService) {
        this.userService = userService;
    }


    @SecurityRequirement(name = "JWT")
    @GetMapping("all")
    public List<LUserDefaultDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("create")
    @Operation(summary = "Allows you to create account")
    public void createUser(@RequestBody LUserRegistrationDto userDto) throws RoleNotFoundException {
        userService.save(userDto);
    }


    @SecurityRequirement(name = "JWT")
    @GetMapping("{id}")
    public LUserDefaultDto getUser(@PathVariable long id) throws UserNotFoundException {
        return userService.findUserById(id);
    }
}
