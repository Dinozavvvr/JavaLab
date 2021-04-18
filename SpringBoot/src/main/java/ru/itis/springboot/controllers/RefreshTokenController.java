package ru.itis.springboot.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springboot.dto.TokenPairDto;
import ru.itis.springboot.dto.TokensRefreshDto;
import ru.itis.springboot.service.TokenService;

import javax.annotation.security.PermitAll;

/**
 * created: 18-04-2021 - 20:11
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
@RestController
public class RefreshTokenController {

    @Autowired
    private TokenService tokenService;

    @PermitAll
    @GetMapping("/api/0.1/refresh_tokens")
    public ResponseEntity<?> refreshTokens(@RequestBody TokensRefreshDto tokenPair) {
        try {
            TokenPairDto tokenPairDto = tokenService.refreshToken(tokenPair);
            return ResponseEntity.ok(tokenPairDto);
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", e.getMessage());
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
    }

}
