package com.example.apotekasmilje.model.users; /***********************************************************************
 * Module:  AuthenticatedUser.java
 * Author:  dajan
 * Purpose: Defines the Class AuthenticatedUser
 ***********************************************************************/

import com.example.apotekasmilje.model.blog.Question;
import com.example.apotekasmilje.model.enums.Gender;
import com.example.apotekasmilje.model.order.Order;
import com.example.apotekasmilje.model.products.ProductEvaluation;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("AuthenticatedUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUser extends Person {
   public static String roleApp = "ROLE_Authenticated_User";

   @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
   @JoinColumn(name="rank_id")
   private Rank rank;

   public AuthenticatedUser(Long id, String firstName, String lastName, String personEmail, String phoneNum, String password, LocalDate birth, Gender gender, Integer point, Boolean isDeleted, UserRole userRole, Timestamp lastPasswordResetDate, Rank rank) {
      super(id, firstName, lastName, personEmail, phoneNum, password, birth, gender, point, isDeleted, userRole, lastPasswordResetDate);
      this.rank = rank;
   }
}