package ru.itis.springboot.security.provider;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.itis.springboot.models.User;
import ru.itis.springboot.security.authentication.JwtAuthentication;
import ru.itis.springboot.security.details.UserDetailsImpl;
import ru.itis.springboot.security.utils.JwtTokenUtils;
import ru.itis.springboot.service.TokenService;

import java.util.Map;

/**
 * created: 18-04-2021 - 13:42
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    public TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        Map<String, Claim> claims = tokenService.verifyToken(token);

        JwtAuthentication jwtAuthentication = new JwtAuthentication(token);

        if (claims != null) {
            User user = User.builder()
                    .email(claims.get(JwtTokenUtils.EMAIL).asString())
                    .state(claims.get(JwtTokenUtils.STATE).as(User.State.class))
                    .role(claims.get(JwtTokenUtils.ROLE).as(User.Role.class))
                    .id(claims.get(JwtTokenUtils.ID).asLong())
                    .build();
            jwtAuthentication.setAuthenticated(true);
            jwtAuthentication.setUserDetails(new UserDetailsImpl(user));
        } else {
            jwtAuthentication.setAuthenticated(false);
        }
        return jwtAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }

}
