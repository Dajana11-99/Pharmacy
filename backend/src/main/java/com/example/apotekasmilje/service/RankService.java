package com.example.apotekasmilje.service;

import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.model.users.Rank;

public interface RankService {
    Rank findByName(String name);
    RankDto findByUserId(Long id);
}
