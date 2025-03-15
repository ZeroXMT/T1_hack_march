package cardwise.com.UserService.dto;

public class LUserDefaultDto {
    public long id;
    public String email;

    public LUserDefaultDto(long id, String username, String email) {
        this.id = id;
        this.email = email;
    }
    public LUserDefaultDto() {}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

