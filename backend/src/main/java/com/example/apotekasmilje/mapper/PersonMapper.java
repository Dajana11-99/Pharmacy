package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.PersonDto;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
