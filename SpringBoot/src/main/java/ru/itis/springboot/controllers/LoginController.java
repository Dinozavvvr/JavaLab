package ru.itis.springboot.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springboot.dto.TokenPairDto;
import ru.itis.springboot.dto.UserLoginDto;
import ru.itis.springboot.models.User;
import ru.itis.springboot.service.LoginService;
import ru.itis.springboot.service.TokenService;

import javax.annotation.security.PermitAll;

/**
 * created: 18-04-2021 - 17:22
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @PermitAll
    @PostMapping("/api/0.1/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        try {
            User user = loginService.login(userLoginDto);
            TokenPairDto tokenDto = tokenService
                    .generateTokenPair(user, userLoginDto.getDeviceFingerprint());
            return ResponseEntity.ok(tokenDto);
        } catch (UsernameNotFoundException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", e.getMessage());
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
    }
}
