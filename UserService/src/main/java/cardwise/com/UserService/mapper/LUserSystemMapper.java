package cardwise.com.UserService.mapper;

import cardwise.com.UserService.dto.LUserSystemDto;
import cardwise.com.UserService.model.LUser;
import cardwise.com.UserService.model.Role;

public class LUserSystemMapper {
    public static LUser toEntity(LUserSystemDto dto) {
        LUser user = new LUser();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setRoles(
                dto.getRoles().stream()
                        .map(Role::new)
                        .toList()
        );
        user.setCreatedAt(dto.getCreateTime());
        user.setUpdatedAt(dto.getUpdateTime());
        user.setEnabled(dto.isEnabled());
        return user;
    }

    public static LUserSystemDto toDto(LUser user) {
        LUserSystemDto dto = new LUserSystemDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRoles(
                user.getRoles().stream()
                        .map(Role::getTitle)
                .toList()
        );
        dto.setCreateTime(user.getCreatedAt());
        dto.setUpdateTime(user.getUpdatedAt());
        dto.setEnabled(user.isEnabled());
        return dto;
    }
}
