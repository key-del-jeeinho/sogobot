package com.xylope.sogobot.domain.authorize.util;

import com.xylope.sogobot.global.dto.AuthorizedUserDto;
import com.xylope.sogobot.global.dto.DomainAuthorizedUserInfoDto;
import com.xylope.sogobot.global.enum_type.DepartmentType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

public class AuthorizeTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    public AuthorizeTokenUtil() {
        secret += System.currentTimeMillis();
    }

    public String makeJwtToken(DomainAuthorizedUserInfoDto dto) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("SOGO-BOT")
                .setIssuedAt(now)
                .setExpiration(getExpirationDate(now))
                .claim("name", dto.getName())
                .claim("email", dto.getEmail())
                .claim("department", dto.getDepartmentType().name())
                .claim("id", dto.getId())
                .signWith(getSignKey())
                .compact();
    }

    public AuthorizedUserDto getUserByToken(String token) {
        Claims payload = parseJwtToken(token).getBody();
        String departmentName = payload.get("department", String.class);

        return new AuthorizedUserDto(
                payload.get("id", Long.class),
                payload.get("name", String.class),
                payload.get("email", String.class),
                DepartmentType.of(departmentName)
        );
    }

    private Jws<Claims> parseJwtToken(String token) {
        validationToken(token);
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token);
    }

    private void validationToken(String token) {
        if(token == null) throw new IllegalArgumentException();
    }

    private Date getExpirationDate(Date date) {
        return new Date(date.getTime() + Duration.ofMinutes(3).toMillis());
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor((secret).getBytes(StandardCharsets.UTF_8));
    }
}
