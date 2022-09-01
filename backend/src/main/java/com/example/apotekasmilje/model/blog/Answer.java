package com.example.apotekasmilje.model.blog;

import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.PharmacyTechnicians;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/***********************************************************************
 * Module:  Answer.java
 * Author:  dajan
 * Purpose: Defines the Class Answer
 ***********************************************************************/


@Entity
@Table(name="answer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
   @Id
   @SequenceGenerator(name = "answer_sequence_generator", sequenceName = "answer_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_sequence_generator")
   @Column(name = "id", unique = true)
   private int id;
   @Column(name = "text")
   private String text;
   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name="pharmacy_id")
   private PharmacyTechnicians pharmacyTechnicians;
   @ManyToOne(cascade = {CascadeType.ALL})
   @JoinColumn(name="question_id")
   private Question question;
}