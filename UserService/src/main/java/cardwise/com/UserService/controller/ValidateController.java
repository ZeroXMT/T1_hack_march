package cardwise.com.UserService.controller;

import cardwise.com.UserService.dto.LUserSystemDto;
import cardwise.com.UserService.error.exception.InvalidTokenException;
import cardwise.com.UserService.error.exception.UserNotFoundException;
import cardwise.com.UserService.request.AccessJwtRequest;
import cardwise.com.UserService.response.JwtAccessResponse;
import cardwise.com.UserService.service.ValidateService;
import cardwise.com.UserService.settings.ApiPath;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPath.VALIDATE_CONTROLLER_PATH)
@Tag(
        name = "Validate Controller",
        description = "allows to validate tokens for other services"
)
public class ValidateController {
    ValidateService validateService;
    public ValidateController(ValidateService validateService) {
        this.validateService = validateService;
    }
    @PostMapping("token")
    public LUserSystemDto validateToken(@RequestBody AccessJwtRequest accessJwtRequest) throws UserNotFoundException, InvalidTokenException {
        return validateService.validateToken(accessJwtRequest);
    }
}
