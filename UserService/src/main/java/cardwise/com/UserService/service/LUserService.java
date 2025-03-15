package cardwise.com.UserService.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import cardwise.com.UserService.dto.LUserDefaultDto;
import cardwise.com.UserService.dto.LUserRegistrationDto;
import cardwise.com.UserService.error.exception.RoleNotFoundException;
import cardwise.com.UserService.error.exception.UserNotFoundException;
import cardwise.com.UserService.mapper.LUserDefaultMapper;
import cardwise.com.UserService.model.LUser;
import cardwise.com.UserService.model.Role;
import cardwise.com.UserService.repository.LUserRepository;
import cardwise.com.UserService.repository.RoleRepository;
import cardwise.com.UserService.settings.Constants;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LUserService {
    private final LUserRepository lUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final String defaultRoleName = Constants.DEFAULT_ROLE_TITLE;

    public LUserService(LUserRepository lUserRepository, PasswordEncoder passwordEncoder,
                        RoleRepository roleRepository) {
        this.lUserRepository = lUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void save(LUserRegistrationDto newUser) throws RoleNotFoundException {
        LUser user = new LUser();
        Role defaultRole = roleRepository.getByTitle(defaultRoleName).orElseThrow(
                () -> new RoleNotFoundException("Default Role not found. Default Role: " + defaultRoleName)
        );
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRoles(List.of(defaultRole));
        lUserRepository.save(user);
    }

    public List<LUserDefaultDto> findAllUsers() {
        return lUserRepository.findAll().stream()
                .map(LUserDefaultMapper::toDto)
                .collect(Collectors.toList());
    }

    public LUserDefaultDto findUserById(long id) throws UserNotFoundException {
        return LUserDefaultMapper.toDto(
                lUserRepository.findById(id).orElseThrow(
                        () -> new UserNotFoundException("Wrong id")
                )
        );
    }
}
