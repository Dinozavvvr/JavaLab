package ru.itis.springboot.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springboot.dto.TokenPairDto;
import ru.itis.springboot.dto.TokensRefreshDto;
import ru.itis.springboot.exceptions.InvalidRefreshTokenException;
import ru.itis.springboot.models.RefreshToken;
import ru.itis.springboot.models.User;
import ru.itis.springboot.repositories.TokenRepository;
import ru.itis.springboot.security.utils.JwtTokenUtils;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * created: 18-04-2021 - 16:47
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public String generateAccessToken(User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim(JwtTokenUtils.ROLE, user.getRole().toString())
                .withClaim(JwtTokenUtils.STATE, user.getState().toString())
                .withClaim(JwtTokenUtils.EMAIL, user.getEmail())
                .withClaim(JwtTokenUtils.ID, user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(
                        new Date(System.currentTimeMillis() + JwtTokenUtils.ACCESS_TOKEN_LIFE_TIME))
                .sign(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY));
    }

    @Override
    public TokenPairDto generateTokenPair(User user, String fingerprint) {
        String refreshTokenToken = UUID.randomUUID().toString();
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(user.getId())
                .fingerprint(fingerprint)
                .token(refreshTokenToken)
                .build();

        tokenRepository.save(refreshToken);
        String accessToken = generateAccessToken(user);

        return new TokenPairDto(accessToken, refreshTokenToken);
    }

    @Override
    public Map<String, Claim> verifyToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY))
                    .build().verify(token);
            return decodedJWT.getExpiresAt()
                    .after(new Date()) ? decodedJWT.getClaims() : null;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    @Override
    public boolean validateToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY))
                .build().verify(token);
        return !decodedJWT.getExpiresAt()
                .after(new Date());
    }

    @Override
    public TokenPairDto refreshToken(TokensRefreshDto tokenPair) {
        RefreshToken refreshToken = tokenRepository.findByToken(tokenPair.getRefreshToken())
                .orElseThrow(() -> new InvalidRefreshTokenException(tokenPair.getRefreshToken()));

        if (!refreshToken.getFingerprint()
                .equals(tokenPair.getDeviceFingerprint())) {
            throw new InvalidRefreshTokenException(tokenPair.getRefreshToken());
        }

        Map<String, Claim> claims = getClaims(tokenPair.getAccessToken());

        User user = User.builder()
                .id(claims.get(JwtTokenUtils.ID).asLong())
                .email(claims.get(JwtTokenUtils.EMAIL).asString())
                .state(claims.get(JwtTokenUtils.STATE).as(User.State.class))
                .role(claims.get(JwtTokenUtils.ROLE).as(User.Role.class))
                .build();

        String newAccessToken = generateAccessToken(user);
        String newRefreshToken = UUID.randomUUID().toString();

        refreshToken.setToken(newRefreshToken);
        tokenRepository.save(refreshToken);

        return TokenPairDto.builder()
                .refreshToken(newRefreshToken)
                .accessToken(newAccessToken)
                .build();
    }

    @Override
    public Map<String, Claim> getClaims(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY))
                    .build().verify(token).getClaims();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
