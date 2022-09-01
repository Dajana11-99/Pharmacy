package com.example.apotekasmilje.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/***********************************************************************
 * Module:  UserRole.java
 * Author:  dajan
 * Purpose: Defines the Class UserRole
 ***********************************************************************/


@Entity
@Table(name="user_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
   @Id
   @SequenceGenerator(name = "userrole_sequence_generator", sequenceName = "userrole_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userrole_sequence_generator")
   @Column(name = "id", unique = true)
   private int id;
   @Column(name="name",nullable = false)
   private String name;
   @Column(columnDefinition = "boolean default false")
   private Boolean isDeleted=false;
}