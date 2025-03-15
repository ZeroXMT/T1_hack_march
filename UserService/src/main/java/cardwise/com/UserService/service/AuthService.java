package cardwise.com.UserService.service;

import cardwise.com.UserService.dto.LUserRegistrationDto;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import cardwise.com.UserService.error.exception.InvalidTokenException;
import cardwise.com.UserService.response.JwtAccessResponse;
import cardwise.com.UserService.response.JwtRefreshResponse;
import cardwise.com.UserService.response.JwtResponse;
import cardwise.com.UserService.security.jwt.JwtProvider;
import cardwise.com.UserService.security.LUserDetails;
import cardwise.com.UserService.security.util.RefreshStorage;
import cardwise.com.UserService.service.security.LUserDetailsService;

@Service
public class AuthService {
    private final LUserDetailsService userDetailsService;
    private final RefreshStorage refreshStorage;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(LUserDetailsService lUserDetailsService, RefreshStorage refreshStorage,
                       JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userDetailsService = lUserDetailsService;
        this.refreshStorage = refreshStorage;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtResponse login(@NonNull LUserRegistrationDto authRequest) throws AuthException {

        final LUserDetails userDetails = (LUserDetails) userDetailsService.loadUserByUsername(authRequest.getEmail());

        if (!userDetails.isEnabled()) {
            refreshStorage.banToken(userDetails.getUsername());
            throw new DisabledException("User is disabled");
        }
        if (passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(userDetails);
            final String refreshToken = jwtProvider.generateRefreshToken(userDetails);
            refreshStorage.put(userDetails.getUsername(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Wrong password");
        }
    }

    public JwtResponse getNewAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            String savedRefreshToken = refreshStorage.get(username);
            if (savedRefreshToken.equals(refreshToken) && refreshStorage.isNotInBlackList(username)) {
                LUserDetails userDetails = (LUserDetails) userDetailsService.loadUserByUsername(username);

                String newAccessToken = jwtProvider.generateAccessToken(userDetails);
                String newRefreshToken = jwtProvider.generateRefreshToken(userDetails);
                refreshStorage.put(userDetails.getUsername(), newRefreshToken);

                JwtResponse response = new JwtResponse();
                response.setAccessToken(newAccessToken);
                response.setRefreshToken(newRefreshToken);
                return response;
            }
        }
        throw new AuthException("Wrong refresh token");
    }

    public void logout(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String username = claims.getSubject();
            String savedRefreshToken = refreshStorage.get(username);
            if (savedRefreshToken.equals(refreshToken) && refreshStorage.isNotInBlackList(username)) {
                refreshStorage.banToken(username);
                return;
            }
        }
        throw new InvalidTokenException("Wrong refresh token");

    }

}


