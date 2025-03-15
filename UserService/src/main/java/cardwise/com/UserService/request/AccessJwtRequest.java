package cardwise.com.UserService.request;

import org.springframework.lang.NonNull;

public class AccessJwtRequest {
    String accessToken;

    public AccessJwtRequest(@NonNull  String accessToken) {
        this.accessToken = accessToken;
    }
    public AccessJwtRequest() {}
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
