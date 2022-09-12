package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.PersonDto;
import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.model.users.Rank;

import java.text.ParseException;
import java.util.List;

public interface PersonService {
    Person registerAuthenticatedUser(PersonDto personDto) throws ParseException;
    Person findByPersonEmail(String email);
    PersonDto personByEmail(String email);
    boolean updatePersonalInformation(PersonDto personDto);
    Boolean checkIfUserHasRank(Long id);
    List<PersonDto> getAll(int pageNo, int pageSize);
    boolean checkUserRank();
    boolean delete(PersonDto personDto);
    boolean add(PersonDto personDto);
    List<PersonDto> search(String name);

    void setUserPoints(AuthenticatedUser person);

    void updatePoints(AuthenticatedUser authenticatedUser);
}
