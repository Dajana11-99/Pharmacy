package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.PersonDto;
import com.example.apotekasmilje.mapper.PersonMapper;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.Person;
import com.example.apotekasmilje.model.users.Rank;
import com.example.apotekasmilje.model.users.UserRole;
import com.example.apotekasmilje.repository.PersonRepository;
import com.example.apotekasmilje.repository.UserRoleRepository;
import com.example.apotekasmilje.service.PersonService;
import com.example.apotekasmilje.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service

public class PersonServiceImpl implements PersonService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RankService rankService;

   private PersonMapper personMapper = new PersonMapper();
    public Person registerAuthenticatedUser(PersonDto personDto)  {

        try {
            if(personRepository.findByPersonEmail(personDto.getPersonEmail())!=null)return null;
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
            person.setAddress(personDto.getAddress());
            person.setPlace(personDto.getPlace());
            person.setZipCode(person.getZipCode());
            personRepository.save(person);
            return true;
    }
   public Person findByPersonEmail(String email){
       return  personRepository.findByPersonEmail(email);
    }
    public PersonDto personByEmail(String email){
        return  personMapper.personToPersonDto(personRepository.findByPersonEmail(email));
    }
    public Boolean checkIfUserHasRank(Long id){
        return personRepository.checkIfUserHasRank(id);
    }
    @Override
    public List<PersonDto> getAll(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("userRole"));
        Page<Person> pagedResult = personRepository.findAll(paging);
        return personMapper.personsToPersonDtos(pagedResult.toList());
    }

    @Override
    public List<PersonDto> search(String name) {
        List<Person> persons = personRepository
         .searchByFirstAndLastNameAndPersonEmail(name);
        return personMapper.personsToPersonDtos(persons);
    }

    public boolean delete(PersonDto personDto){
        try{
            Person person=personRepository.findById(personDto.getId()).get();
            if(person==null)return false;
            personRepository.deleteById(personDto.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean add(PersonDto personDto){
        try{
            if(personRepository.findByPersonEmail(personDto.getPersonEmail())!=null)return false;
            AuthenticatedUser user= personMapper.personDtoToAuthenticatedUser(personDto);
            UserRole auth = userRoleRepository.findByName("ROLE_Pharmacy_Technicians");
            if(auth==null)return false;
            user.setUserRole(auth);
            user.setPassword(passwordEncoder.encode(personDto.getPassword()));
            personRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean checkUserRank(){
        try {
            for (AuthenticatedUser authenticatedUser : personRepository.findAllUsers()) {
                List<Rank> ranks = rankService.getAll();
                    for(int i=0;i<ranks.size();i++){
                        if(authenticatedUser.getPoint()>=ranks.get(i).getPoints()
                            && authenticatedUser.getPoint() < ranks.get(i+1).getPoints()){
                            authenticatedUser.setRank(ranks.get(i));
                            personRepository.save(authenticatedUser);
                        }else if(authenticatedUser.getPoint()>=ranks.get(ranks.size()-1).getPoints()){
                            authenticatedUser.setRank(ranks.get(ranks.size()-1));
                            personRepository.save(authenticatedUser);
                        }
                    }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
