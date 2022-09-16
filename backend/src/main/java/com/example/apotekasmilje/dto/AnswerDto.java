package com.example.apotekasmilje.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private Long   id;
    private String text;
    private String pharmacyTechnicians;
    private Long   questionId;
}
