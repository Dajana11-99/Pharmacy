package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.model.users.UserRole;
import com.example.apotekasmilje.model.users.UserTokenState;
import com.example.apotekasmilje.repository.PersonRepository;
import com.example.apotekasmilje.security.TokenUtils;
import com.example.apotekasmilje.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class LogInServiceImpl implements LoginService {

    @Autowired
    private  TokenUtils tokenUtils;
   @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  PersonRepository personRepository;


    public UserTokenState logIn(String username, String password) {
        System.out.println("aaaa"+username+" "+ password);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Person user = (Person) authentication.getPrincipal();
        String userType= personRepository.findRoleById(user.getId());
        String accessToken = tokenUtils.generateToken(user.getUsername());
        int accessExpiresIn = tokenUtils.getExpiredIn();
        return new UserTokenState(userType,accessToken, accessExpiresIn,user.getPersonEmail());
    }


}
