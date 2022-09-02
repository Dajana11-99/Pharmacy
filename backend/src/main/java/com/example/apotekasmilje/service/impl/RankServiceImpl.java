package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.mapper.RankMapper;
import com.example.apotekasmilje.model.users.Rank;
import com.example.apotekasmilje.repository.RankRepository;
import com.example.apotekasmilje.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankRepository rankRepository;

    private RankMapper rankMapper = new RankMapper();
    public  Rank findByName(String name){
        return  rankRepository.findByName(name);
    }

    public RankDto findByUserId(Long id){
        System.out.println("AA"+rankRepository.findRanByUser(id).getName());
        return  rankMapper.rankToRankDto(rankRepository.findRanByUser(id));
    }
}
