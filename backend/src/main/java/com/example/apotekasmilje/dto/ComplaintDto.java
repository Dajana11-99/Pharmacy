package com.example.apotekasmilje.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintDto {
    private Long   id;
    private String reason;
    private boolean status;
    private Long productId;
    private Long saleId;
    private String productName;
    private String saleName;
}
