package com.example.apotekasmilje.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDto {
    private Long   id;
    private String comment;
    private int    grade;
    private Long   productId;
    private String email;
    private String date;
}
