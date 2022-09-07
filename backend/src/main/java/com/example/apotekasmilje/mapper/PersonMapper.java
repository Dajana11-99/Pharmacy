package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.PersonDto;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PersonMapper {
    public Person personDtoToPerson(PersonDto personDto)  {
        try{

            return  new Person(null, personDto.getFirstName(), personDto.getLastName(),
                    personDto.getPersonEmail(), personDto.getPhoneNum(),
                    personDto.getPassword(),LocalDate.parse(personDto.getBirth()),
            personDto.getGender(), personDto.getPoint(),null,null);
        }catch (Exception e){
            return null;
        }

    }
    public List<PersonDto> personsToPersonDtos(List<Person>persons){
        List<PersonDto> personDtos= new ArrayList<>();
        for(Person person: persons)
         personDtos.add(personToPersonDto(person));
        return personDtos;
    }

    public PersonDto personToPersonDto(Person person){
        return  new PersonDto(person.getId(), person.getFirstName(), person.getLastName(),
                person.getPersonEmail(), person.getPhoneNum(),null,person.getBirth().toString(),
                person.getGender(),person.getUserRole().getName(), person.getPoint(), null);
    }
    public AuthenticatedUser personDtoToAuthenticatedUser(PersonDto personDto){
        try {
            LocalDate birth = LocalDate.parse(personDto.getBirth());
            return new AuthenticatedUser(null, personDto.getFirstName(), personDto.getLastName(),
                    personDto.getPersonEmail(), personDto.getPhoneNum(),
                    personDto.getPassword(), birth,
                    personDto.getGender(), personDto.getPoint(), null, null,null);
        }catch (Exception e){
            return null;
        }
    }

}
