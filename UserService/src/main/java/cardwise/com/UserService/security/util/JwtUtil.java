package cardwise.com.UserService.security.util;

import io.jsonwebtoken.Claims;
import org.springframework.lang.NonNull;
import cardwise.com.UserService.model.Role;
import cardwise.com.UserService.security.jwt.JwtAuthentication;

import java.util.List;
import java.util.Map;

public class JwtUtil {
    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtAuthentication = new JwtAuthentication();
        jwtAuthentication.setId(claims.get("id", Long.class));
        jwtAuthentication.setEmail(claims.getSubject());
        jwtAuthentication.setRoles(getRoles(claims));
        return jwtAuthentication;
    }

    private static List<Role> getRoles(@NonNull Claims claims) {
        final List<Map<String, Object>> rolesData = claims.get("roles", List.class);
        return rolesData.stream()
                .map(roleMap -> {
                    Role role = new Role();
                    role.setId(((Number) roleMap.get("id")).intValue());
                    role.setTitle((String) roleMap.get("title"));
                    return role;
        })
                .toList();
    }
}
