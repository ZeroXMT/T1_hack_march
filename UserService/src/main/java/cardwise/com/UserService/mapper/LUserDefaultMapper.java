package cardwise.com.UserService.mapper;

import cardwise.com.UserService.dto.LUserDefaultDto;
import cardwise.com.UserService.model.LUser;

public class LUserDefaultMapper {
    public static LUserDefaultDto toDto(LUser user) {
        if (user == null) {
            return null;
        }
        LUserDefaultDto dto = new LUserDefaultDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static LUser toEntity(LUserDefaultDto dto) {
        if (dto == null) {
            return null;
        }
        LUser user = new LUser();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        return user;
    }
}
