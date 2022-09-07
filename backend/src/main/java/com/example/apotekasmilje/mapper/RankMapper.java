package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.model.users.Rank;

import java.util.ArrayList;
import java.util.List;

public class RankMapper {

    public RankDto rankToRankDto(Rank rank){
        return new RankDto(rank.getId(), rank.getName(), rank.getPoints(),
                rank.getDiscountPercentage(), rank.getPointsForCompletedOrder(), rank.getPointsForCancelledOrders());
    }
    public List<RankDto> ranksToRankDtos(List<Rank>ranks){
        List<RankDto> rankDtos = new ArrayList<>();
        for(Rank rank:ranks)
            rankDtos.add(rankToRankDto(rank));
        return rankDtos;
    }

    public Rank rankDtoToRank(RankDto rankDto){
        return new Rank(rankDto.getId(),rankDto.getName(),
                rankDto.getPoints(), rankDto.getDiscountPercentage(),
                rankDto.getPointsForCompletedOrder(), rankDto.getPointsForCancelledOrders());
    }
}
