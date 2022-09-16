package com.example.apotekasmilje.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private Long   id;
    private String text;
    private Long    blogId;
    private String fullName;
    private String  userEmail;
}
