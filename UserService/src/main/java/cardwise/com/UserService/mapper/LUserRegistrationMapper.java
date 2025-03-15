package cardwise.com.UserService.mapper;

import cardwise.com.UserService.dto.LUserRegistrationDto;
import cardwise.com.UserService.model.LUser;

public class LUserRegistrationMapper {
    public static LUserRegistrationDto toDto(LUser user) {
        if (user == null) {
            return null;
        }
        LUserRegistrationDto dto = new LUserRegistrationDto();
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static LUser toEntity(LUserRegistrationDto dto) {
        if (dto == null) {
            return null;
        }
        LUser user = new LUser();
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }
}
