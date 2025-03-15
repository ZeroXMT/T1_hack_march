package cardwise.com.UserService.dto;

import jakarta.validation.constraints.NotNull;

public class LUserRegistrationDto {
    @NotNull
    private String email;
    @NotNull
    private String password;

    public LUserRegistrationDto() {}
    public LUserRegistrationDto(String username, String email, String password) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
