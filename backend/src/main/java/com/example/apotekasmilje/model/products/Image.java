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
import java.io.Serializable;
import java.util.*;
@Entity
@Table(name="image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {
   @Id
   @SequenceGenerator(name = "image_sequence_generator", sequenceName = "image_sequence", initialValue = 300)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
   @Column(name = "url", nullable = false)
   private String url;
}