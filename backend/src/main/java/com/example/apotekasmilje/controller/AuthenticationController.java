package com.example.apotekasmilje.controller;

import javax.servlet.http.HttpServletRequest;

import com.example.apotekasmilje.dto.ChangePasswordDto;
import com.example.apotekasmilje.dto.LogInDto;
import com.example.apotekasmilje.dto.PersonDto;
import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.model.users.UserTokenState;
import com.example.apotekasmilje.security.TokenUtils;
import com.example.apotekasmilje.service.LoginService;
import com.example.apotekasmilje.service.PersonService;
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
    private static final String EMAIL_ALREADY_IN_USE = "Мејл је већ у употреби!";
    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private LoginService loginService;

    @Autowired
    private PersonService personService;


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
        String userType = user.getUserRole().getName();
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

    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody PersonDto personDto) {

        Person existUser=personService.findByPersonEmail(personDto.getPersonEmail());
        if(existUser!=null) return ResponseEntity.badRequest().body(EMAIL_ALREADY_IN_USE);
        try {
                if(personService.registerAuthenticatedUser(personDto)==null)
                    return new ResponseEntity<>("Корисник са тим мејлом већ постоји!", HttpStatus.BAD_REQUEST);
                return ResponseEntity.status(201).body(SUCCESS);
        }catch (Exception e){
            return new ResponseEntity<>("Технички проблем, покушајте поново касније!", HttpStatus.BAD_REQUEST);
        }


    }
    @PostMapping("/personByEmail/{email}/")
    public ResponseEntity<PersonDto> personByEmail(@PathVariable String email)  {
        try{
           PersonDto person =  personService.personByEmail(email);
            return  new ResponseEntity<>(person, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePassword) {
        if(userDetailsService.changePassword(changePassword.getOldPassword(),changePassword.getNewPassword()))
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        else
            return new ResponseEntity<>("Менаџер за аутентификацију није успио да промијени лозинку.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/updatePersonalInformation")
    public ResponseEntity<String> updatePersonalInformation(@RequestBody PersonDto personDto) {
        if(personService.updatePersonalInformation(personDto))
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        else
            return new ResponseEntity<>("Ажурирање података није извршено. Дошло је до грешке. Покушајте поново касније", HttpStatus.BAD_REQUEST);
    }


}