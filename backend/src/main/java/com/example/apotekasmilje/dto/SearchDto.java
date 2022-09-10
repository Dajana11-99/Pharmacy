package com.example.apotekasmilje.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
    private String search;
    private float from;
    private float to;
    private Long categoryId;
    private int  pageNo;
    private int  pageSize;
}
