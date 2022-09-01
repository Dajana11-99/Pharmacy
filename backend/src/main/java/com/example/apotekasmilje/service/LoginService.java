package com.example.apotekasmilje.service;


import com.example.apotekasmilje.model.users.UserTokenState;

public interface LoginService {
    UserTokenState logIn(String username, String password);
}
