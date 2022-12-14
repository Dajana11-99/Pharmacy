package com.example.apotekasmilje.model.blog; /***********************************************************************
 * Module:  Blog.java
 * Author:  dajan
 * Purpose: Defines the Class Blog
 ***********************************************************************/

import com.example.apotekasmilje.model.enums.Type;
import com.example.apotekasmilje.model.products.Image;
import com.example.apotekasmilje.model.users.PharmacyTechnicians;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name="blog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
   @Id
   @SequenceGenerator(name = "blog_sequence_generator", sequenceName = "blog_sequence", initialValue = 100)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_sequence_generator")
   @Column(name = "id", unique = true)
   private Long id;
   @Column(name = "text",length = 2000000)
   private String text;
   @Column(name = "title",length = 512)
   private String title;
   @Column(name = "status")
   private boolean status;
   @Column(name = "description",length = 1000)
   private String description;
   @Column(name = "type")
   private Type type;
   @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
   @JoinTable(name = "blog_images",
           joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))
   private List<Image> image;
   @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
   @JoinColumn(name="pharmacy_id")
   private PharmacyTechnicians pharmacyTechnicians;
}