package com.example.apotekasmilje.model.users; /***********************************************************************
 * Module:  AuthenticatedUser.java
 * Author:  dajan
 * Purpose: Defines the Class AuthenticatedUser
 ***********************************************************************/

import com.example.apotekasmilje.model.blog.Question;
import com.example.apotekasmilje.model.order.Order;
import com.example.apotekasmilje.model.products.ProductEvaluation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@DiscriminatorValue("AuthenticatedUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUser extends Person {
   public static String roleApp = "ROLE_Authenticated_User";
   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name="rank_id")
   private Rank rank;
}