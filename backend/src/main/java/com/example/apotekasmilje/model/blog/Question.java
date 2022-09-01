package com.example.apotekasmilje.model.blog; /***********************************************************************
 * Module:  Question.java
 * Author:  dajan
 * Purpose: Defines the Class Question
 ***********************************************************************/

import com.example.apotekasmilje.model.users.AuthenticatedUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
   @Id
   @SequenceGenerator(name = "question_sequence_generator", sequenceName = "question_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_sequence_generator")
   @Column(name = "id", unique = true)
   private int id;
   @Column(name = "text")
   private String text;
   @Column(name = "status")
   private boolean status;
   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name="blog_id")
   public  Blog blog;
   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name="user_id")
   public AuthenticatedUser authenticatedUser;
   }