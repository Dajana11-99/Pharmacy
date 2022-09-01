package com.example.apotekasmilje.controller;

import javax.servlet.http.HttpServletRequest;

import com.example.apotekasmilje.dto.LogInDto;
import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.model.users.UserTokenState;
import com.example.apotekasmilje.security.TokenUtils;
import com.example.apotekasmilje.service.LoginService;
import com.example.apotekasmilje.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    private static final String SUCCESS = "Success.";
    private static final String EMAIL_ALREADY_IN_USE = "Email already in use.";

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody LogInDto userRequest) {
        try{
        UserTokenState userTokenState = loginService.logIn(userRequest.getEmail(),userRequest.getPassword());
            return ResponseEntity.ok(userTokenState);
        }catch (Exception e){
            return new ResponseEntity<>(new UserTokenState(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<UserTokenState> refreshAuthenticationToken(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);
        Person user = (Person) this.userDetailsService.loadUserByUsername(username);
        String userType = user.getClass().getSimpleName();
        try{
            this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate());
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();
            return ResponseEntity.ok(new UserTokenState(userType, refreshedToken, expiresIn,user.getPersonEmail()));
        } catch (Exception e){
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }

    /*@PostMapping("/signUpCabinOwner")
    public ResponseEntity<String> registerCabinOwner(@RequestBody UserRequestDTO userRequest) {

        User existUser=userService.findByUsername(userRequest.getUsername());
        if(existUser== null)
        {
            this.userService.registerCabinOwner(cabinOwnerMapper.userRequestDTOToCabinOwner(userRequest));
            return ResponseEntity.status(201).body(SUCCESS);
        }
        return ResponseEntity.badRequest().body(EMAIL_ALREADY_IN_USE);
    }*/

  /*  @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePassword) {
        if(userDetailsService.changePassword(changePassword.getOldPassword(),changePassword.getNewPassword()))
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        else
            return new ResponseEntity<>("No authentication manager set. can't change Password", HttpStatus.BAD_REQUEST);
    }*/

}