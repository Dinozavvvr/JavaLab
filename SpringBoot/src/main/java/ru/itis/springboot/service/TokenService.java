package ru.itis.springboot.service;

import com.auth0.jwt.interfaces.Claim;
import ru.itis.springboot.dto.TokenPairDto;
import ru.itis.springboot.dto.TokensRefreshDto;
import ru.itis.springboot.models.RefreshToken;
import ru.itis.springboot.models.User;

import java.util.Map;

/**
 * created: 18-04-2021 - 16:45
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
public interface TokenService {

    String generateAccessToken(User user);


    RefreshToken generateRefreshToken(User user, String fingerprint);

    Map<String, Claim> verifyToken(String token);

    boolean validateToken(String token);

    TokenPairDto refreshToken(TokensRefreshDto tokenPair);

    Map<String, Claim> getClaims(String token);

    public TokenPairDto generateTokenPair(User user, String fingerprint);

}
