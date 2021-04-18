package ru.itis.springboot.security.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * created: 18-04-2021 - 15:59
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
@Component
public class JwtTokenUtils {

    public static String SECRET_KEY;

    public static final String ROLE = "role";

    public static final String EMAIL = "email";

    public static final String STATE = "state";

    public static final String ID = "id";

    public static Long ACCESS_TOKEN_LIFE_TIME = 1000 * 60 * 30L;          // default 30 minutes

    public static Long REFRESH_TOKEN_LIFE_TIME = 1000 * 60 * 24 * 60L;    // default 60 days

    @Value("${jwt.access.lifetime}")
    public void setAccessTokenLifeTime(Long accessTokenLifeTime) {
        ACCESS_TOKEN_LIFE_TIME = accessTokenLifeTime;
    }

    @Value("${jwt.refresh.lifetime}")
    public void setRefreshTokenLifeTime(Long refreshTokenLifeTime) {
        REFRESH_TOKEN_LIFE_TIME = refreshTokenLifeTime;
    }

    @Value("${jwt.secret.key}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

}
