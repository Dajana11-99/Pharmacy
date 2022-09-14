package com.example.apotekasmilje.dto;

import com.example.apotekasmilje.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private Long   id;
    private String text;
    private String title;
    private String description;
    private boolean status;
    private Type   type;
    private List<ImageDto> image;
    private String pharmacyTechnicians;
}
