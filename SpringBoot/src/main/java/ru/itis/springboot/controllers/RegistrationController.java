package ru.itis.springboot.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springboot.dto.UserSignupDto;
import ru.itis.springboot.exceptions.UserAlreadyExistException;
import ru.itis.springboot.models.User;
import ru.itis.springboot.service.RegistrationService;

import javax.annotation.security.PermitAll;

/**
 * created: 18-04-2021 - 12:38
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PermitAll
    @PostMapping("/api/0.1/signup")
    public ResponseEntity<?> signup(@RequestBody UserSignupDto userSignupDto) {
        try {
            User user = registrationService.signup(userSignupDto);
            return ResponseEntity.ok(user);

        } catch (UserAlreadyExistException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", e.getMessage());
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
    }

}
