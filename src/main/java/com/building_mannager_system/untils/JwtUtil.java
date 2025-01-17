package com.building_mannager_system.untils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
   // Khóa bí mật cần đủ mạnh, sử dụng thuật toán HS512
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    private final long expirationTime = 1000 * 60 * 60 * 24;  // Token hết hạn sau 24 giờ

    // Tạo JWT Token với thông tin người dùng
    public String generateToken(String email, List<String> permissions, Map<String, Object> userDetails) {
        return Jwts.builder()
                .setSubject(email)
                .claim("permission", permissions)      // Thêm vai trò (permissions)
                .claim("user", userDetails)             // Thêm đối tượng người dùng
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey)
                .compact();
    }

    // Xác thực và giải mã token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token đã hết hạn!");
        } catch (MalformedJwtException e) {
            System.out.println("Chữ ký token không hợp lệ!");
        } catch (JwtException e) {
            System.out.println("Lỗi xác thực token!");
        }
        return false;

    }

    // Giải mã token và trích xuất email
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<String> extractPermissions(String token) {
        Object permissionsObject = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("permission");

        if (permissionsObject instanceof List<?>) {
            return ((List<?>) permissionsObject).stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Invalid permissions data type");
        }
    }



    // Giải mã và trích xuất đối tượng người dùng an toàn
    public Map<String, Object> extractUserDetails(String token) {
        Object userObject = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("user");

        if (userObject instanceof Map<?, ?>) {
            // Ép kiểu an toàn với Map
            return ((Map<?, ?>) userObject).entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> String.valueOf(e.getKey()),
                            Map.Entry::getValue
                    ));
        } else {
            throw new IllegalArgumentException("Dữ liệu 'user' không đúng định dạng");
        }
    }

}
