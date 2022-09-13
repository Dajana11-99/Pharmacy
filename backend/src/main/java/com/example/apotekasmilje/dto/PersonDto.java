package com.example.apotekasmilje.dto;

import com.example.apotekasmilje.model.enums.Gender;
import com.example.apotekasmilje.model.users.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Long   id;
    private String firstName;
    private String lastName;
    private String personEmail;
    private String phoneNum;
    private String password;
    private String birth;
    private Gender gender;
    private String userRole;
    private Integer point;
    private Timestamp lastPasswordResetDate;
    private String address;
    private String place;
    private String  zipCode;
}
