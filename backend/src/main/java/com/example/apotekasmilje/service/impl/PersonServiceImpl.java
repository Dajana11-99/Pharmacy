package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.PersonDto;
import com.example.apotekasmilje.mapper.PersonMapper;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.model.users.Rank;
import com.example.apotekasmilje.model.users.UserRole;
import com.example.apotekasmilje.repository.AuthenticatedRepository;
import com.example.apotekasmilje.repository.PersonRepository;
import com.example.apotekasmilje.repository.UserRoleRepository;
import com.example.apotekasmilje.service.PersonService;
import com.example.apotekasmilje.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service

public class PersonServiceImpl implements PersonService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private AuthenticatedRepository authenticatedRepository;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RankService rankService;

   private PersonMapper personMapper = new PersonMapper();
    public Person registerAuthenticatedUser(PersonDto personDto)  {

        try {
            AuthenticatedUser user= personMapper.personDtoToAuthenticatedUser(personDto);
            UserRole auth = userRoleRepository.findByName("ROLE_Authenticated_User");
            Rank rank = rankService.findByName("БРОНЗАНИ");
            if(auth==null || rank== null)return null;
           user.setUserRole(auth);
           user.setRank(rank);
            user.setPassword(passwordEncoder.encode(personDto.getPassword()));
            personRepository.save(user);
            return user;
        }catch (Exception e){
            return null;
        }

    }
    public boolean updatePersonalInformation(PersonDto personDto)  {
            Person person = personRepository.findByPersonEmail(personDto.getPersonEmail());
            if(person==null)return false;
            person.setBirth(LocalDate.parse(personDto.getBirth()));
            person.setFirstName(personDto.getFirstName());
            person.setLastName(personDto.getLastName());
            person.setPhoneNum(personDto.getPhoneNum());
            person.setGender(personDto.getGender());
            personRepository.save(person);
            return true;
    }
   public Person findByPersonEmail(String email){
       return  personRepository.findByPersonEmail(email);
    }
    public PersonDto personByEmail(String email){
        return  personMapper.personToPersonDto(personRepository.findByPersonEmail(email));
    }

}
