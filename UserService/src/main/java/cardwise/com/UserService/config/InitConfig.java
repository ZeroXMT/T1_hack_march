package cardwise.com.UserService.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import cardwise.com.UserService.model.LUser;
import cardwise.com.UserService.model.Role;
import cardwise.com.UserService.repository.LUserRepository;
import cardwise.com.UserService.repository.RoleRepository;
import cardwise.com.UserService.settings.Constants;
import cardwise.com.UserService.settings.EmailUtils;

import java.util.List;

@Configuration
public class InitConfig {
    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository, LUserRepository lUserRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = roleRepository.getByTitle(Constants.ADMIN_ROLE_TITLE).orElseGet(() -> {
                Role role = new Role();
                role.setTitle(Constants.ADMIN_ROLE_TITLE);
                return roleRepository.save(role);
            });

            Role userRole = roleRepository.getByTitle(Constants.USER_ROLE_TITLE).orElseGet(() -> {
                Role role = new Role();
                role.setTitle(Constants.USER_ROLE_TITLE);
                return roleRepository.save(role);
            });

            String adminEmail = EmailUtils.generateEmail("testadmin");
            if (Constants.DEBUG && lUserRepository.findByEmail(adminEmail).orElse(null) == null) {
                LUser user = new LUser();
                user.setPassword(passwordEncoder.encode("mypassword"));
                user.setEmail(adminEmail);
                user.setRoles(List.of(adminRole, userRole));
                lUserRepository.save(user);
            }

            String userEmail = EmailUtils.generateEmail("testuser");
            if (Constants.DEBUG && lUserRepository.findByEmail(userEmail).orElse(null) == null) {
                LUser user = new LUser();
                user.setPassword(passwordEncoder.encode("mypassword"));
                user.setEmail(userEmail);
                user.setRoles(List.of(userRole));
                lUserRepository.save(user);
            }

        };
    }
}
