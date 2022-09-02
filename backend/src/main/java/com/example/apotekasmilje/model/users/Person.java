package com.example.apotekasmilje.model.users;

import com.example.apotekasmilje.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
public class Person implements Serializable, UserDetails {
   @Id
   @SequenceGenerator(name = "person_sequence_generator", sequenceName = "person_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
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
   private LocalDate birth;
   @Column(name="gender")
   private Gender gender;
   @Column(name="point")
   private Integer point;
   @Column(columnDefinition = "boolean default false")
   private Boolean isDeleted=false;
   @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE} )
   @JoinColumn(name="role_id")
   private UserRole userRole;
   @Column(name = "last_password_reset_date")
   private Timestamp lastPasswordResetDate;


   public void setPassword(String password) {
      Timestamp now = new Timestamp(new Date().getTime());
      this.setLastPasswordResetDate(now);
      this.password = password;
   }
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<UserRole> collection = new ArrayList<>();
      collection.add(this.userRole);
      return collection;
   }

   @Override
   public String getUsername() {
      return this.personEmail;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }




}