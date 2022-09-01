package com.example.apotekasmilje.model.users; /***********************************************************************
 * Module:  PharmacyTechnicians.java
 * Author:  dajan
 * Purpose: Defines the Class PharmacyTechnicians
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("PharmacyTechnicians")
@Getter
@Setter
@AllArgsConstructor
public class PharmacyTechnicians extends Person {
   public static String roleApp = "ROLE_Pharmacy_Technicians";

}