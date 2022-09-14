package com.example.apotekasmilje.model.users; /***********************************************************************
 * Module:  PharmacyTechnicians.java
 * Author:  dajan
 * Purpose: Defines the Class PharmacyTechnicians
 ***********************************************************************/

import com.example.apotekasmilje.model.enums.Gender;

import lombok.Getter;

import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("PharmacyTechnicians")
@Getter
@Setter
public class PharmacyTechnicians extends Person {
   public static String roleApp = "ROLE_Pharmacy_Technicians";



   public PharmacyTechnicians(Long id, String firstName, String lastName, String personEmail, String phoneNum, String password, LocalDate birth, Gender gender, Integer point, String address, String place, String zipCode, UserRole userRole, Timestamp lastPasswordResetDate) {
      super(id, firstName, lastName, personEmail, phoneNum, password, birth, gender, point, address, place, zipCode, userRole, lastPasswordResetDate);
   }

   public PharmacyTechnicians() {

   }
}