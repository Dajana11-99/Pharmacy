package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.PersonDto;
import com.example.apotekasmilje.model.users.Person;

import java.text.ParseException;

public interface PersonService {
    Person registerAuthenticatedUser(PersonDto personDto) throws ParseException;
    Person findByPersonEmail(String email);
    PersonDto personByEmail(String email);
    boolean updatePersonalInformation(PersonDto personDto);
}
