package cardwise.com.UserService.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import cardwise.com.UserService.model.LUser;
import cardwise.com.UserService.repository.LUserRepository;
import cardwise.com.UserService.security.LUserDetails;

import java.util.Optional;

@Service
public class LUserDetailsService implements UserDetailsService {
    @Autowired
    LUserRepository lUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LUser> oUser = lUserRepository.findByEmail(username);
        LUser user = oUser.orElseThrow(() -> new UsernameNotFoundException(username));
        return new LUserDetails(user);
    }
}
