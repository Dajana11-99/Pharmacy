package com.example.apotekasmilje.model.products; /***********************************************************************
 * Module:  Image.java
 * Author:  dajan
 * Purpose: Defines the Class Image
 ***********************************************************************/

import com.example.apotekasmilje.model.blog.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name="image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
   @Id
   @SequenceGenerator(name = "image_sequence_generator", sequenceName = "image_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_sequence_generator")
   @Column(name = "image_id", unique = true)
   private int id;
   @Column(name = "id", nullable = false)
   private String url;
   @Column(columnDefinition = "boolean default false")
   private Boolean isDeleted=false;
}