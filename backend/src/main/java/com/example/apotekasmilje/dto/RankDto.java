package com.example.apotekasmilje.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RankDto {
    private Long id;
    private String name;
    private int points;
    private int discountPercentage;
    private int pointsForCompletedOrder;
    private int pointsForCancelledOrders;
}
