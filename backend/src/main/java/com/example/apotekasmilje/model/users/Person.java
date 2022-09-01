package com.example.apotekasmilje.model.users;

import com.example.apotekasmilje.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/***********************************************************************
 * Module:  Person.java
 * Author:  dajan
 * Purpose: Defines the Class Person
 ***********************************************************************/

@Entity
@Table(name="person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person implements UserDetails {
   @Id
   @SequenceGenerator(name = "person_sequence_generator", sequenceName = "person_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence_generator")
   @Column(name = "id", unique = true)
   private int id;
   @Column(name="firstName",nullable = false)
   private String firstName;
   @Column(name="lastName",nullable = false)
   private String lastName;
   @Column(name="personEmail",nullable = false)
   private String personEmail;
   @Column(name="phoneNum")
   private String phoneNum;
   @Column(name="password")
   private String password;
   @Column(name="birth")
   private Date birth;
   @Column(name="gender")
   private Gender gender;
   @Column(columnDefinition = "boolean default false")
   private Boolean isDeleted=false;
   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name="role_id")
   private UserRole userRole;
   @Column(name = "last_password_reset_date")
   private Timestamp lastPasswordResetDate;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
   }

   @Override
   public String getPassword() {
      return null;
   }

   @Override
   public String getUsername() {
      return null;
   }

   @Override
   public boolean isAccountNonExpired() {
      return false;
   }

   @Override
   public boolean isAccountNonLocked() {
      return false;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return false;
   }

   @Override
   public boolean isEnabled() {
      return false;
   }
}