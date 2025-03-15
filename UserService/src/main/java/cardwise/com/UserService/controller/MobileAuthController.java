package cardwise.com.UserService.controller;

import cardwise.com.UserService.dto.LUserRegistrationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cardwise.com.UserService.request.RefreshJwtRequest;
import cardwise.com.UserService.response.JwtAccessResponse;
import cardwise.com.UserService.response.JwtRefreshResponse;
import cardwise.com.UserService.response.JwtResponse;
import cardwise.com.UserService.service.AuthService;
import cardwise.com.UserService.settings.ApiPath;

@RestController
@RequestMapping(ApiPath.AUTH_MOBILE_CONTROLLER_PATH)
@Tag(
        name = "Mobile Authentication",
        description = "allows you to login, logout or get new tokens"
)
public class MobileAuthController {
    private final AuthService authService;

    public MobileAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    @Operation(summary = "allows you to get access and refresh token")
    public ResponseEntity<JwtResponse> login(@RequestBody LUserRegistrationDto authRequest) throws AuthException {
        final JwtResponse tokens = authService.login(authRequest);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("access")
    @Operation(summary = "allows you to get new access token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse tokens = authService.getNewAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("logout")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<JwtResponse> logout(@RequestBody RefreshJwtRequest request) throws AuthException {
        authService.logout(request.getRefreshToken());
        return ResponseEntity.ok().build();
    }
}
