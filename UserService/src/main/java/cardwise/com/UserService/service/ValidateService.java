package cardwise.com.UserService.service;

import cardwise.com.UserService.dto.LUserSystemDto;
import cardwise.com.UserService.error.exception.InvalidTokenException;
import cardwise.com.UserService.error.exception.UserNotFoundException;
import cardwise.com.UserService.mapper.LUserSystemMapper;
import cardwise.com.UserService.model.LUser;
import cardwise.com.UserService.repository.LUserRepository;
import cardwise.com.UserService.repository.RoleRepository;
import cardwise.com.UserService.request.AccessJwtRequest;
import cardwise.com.UserService.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;


@Service
public class ValidateService {
    private final JwtProvider jwtProvider;
    private final LUserRepository lUserRepository;

    public ValidateService(JwtProvider jwtProvider, LUserRepository lUserRepository, RoleRepository roleRepository) {
        this.jwtProvider = jwtProvider;
        this.lUserRepository = lUserRepository;
    }

    public LUserSystemDto validateToken(AccessJwtRequest accessJwtRequest) throws UserNotFoundException, InvalidTokenException {
        String token = accessJwtRequest.getAccessToken();
        if (jwtProvider.validateAccessToken(token)) {
            Claims claims = jwtProvider.getAccessClaims(token);
            LUser user = lUserRepository.findByEmail(claims.getSubject()).orElseThrow(
                    () -> new UserNotFoundException("Wrong credentials")
            );
            return LUserSystemMapper.toDto(user);
        }
        throw new InvalidTokenException("Wrong access token");

    }

}
