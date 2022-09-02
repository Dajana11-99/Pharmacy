package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.model.users.Rank;

public class RankMapper {

    public RankDto rankToRankDto(Rank rank){
        return new RankDto(rank.getId(), rank.getName(), rank.getPoints(),
                rank.getDiscountPercentage(), rank.getPointsForCompletedOrder(), rank.getPointsForCancelledOrders());
    }
}
