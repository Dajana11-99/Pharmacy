package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.model.users.Rank;

import java.util.List;

public interface RankService {
    Rank findByName(String name);
    RankDto findByUserId(Long id);
    List<RankDto> getAll(int pageNo, int pageSize);
    List<Rank> getAll();
    boolean add(RankDto rankDto);
    boolean update(RankDto rankDto);
    boolean delete(RankDto rankDto);
}
