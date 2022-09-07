package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.dto.ProductDto;
import com.example.apotekasmilje.dto.RankDto;
import com.example.apotekasmilje.mapper.RankMapper;
import com.example.apotekasmilje.model.products.Product;
import com.example.apotekasmilje.model.users.AuthenticatedUser;
import com.example.apotekasmilje.model.users.Rank;
import com.example.apotekasmilje.repository.RankRepository;
import com.example.apotekasmilje.service.PersonService;
import com.example.apotekasmilje.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankRepository rankRepository;
    @Autowired
    private PersonService personService;

    private RankMapper rankMapper = new RankMapper();
    public  Rank findByName(String name){
        return  rankRepository.findByName(name);
    }
    public RankDto findByUserId(Long id){
        return  rankMapper.rankToRankDto(rankRepository.findRanByUser(id));
    }
    public List<RankDto> getAll(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("points"));
        Page<Rank> pagedResult = rankRepository.findAll(paging);
        return rankMapper.ranksToRankDtos(pagedResult.toList());
    }

    @Override
    public List<Rank> getAll() {
     List<Rank>  ranks = rankRepository.findByOrderByPointsAsc();
        return ranks;
    }


    public boolean add(RankDto rankDto) {
        try {
            if (rankRepository.findByName(rankDto.getName()) != null) return false;
            Rank rank = rankMapper.rankDtoToRank(rankDto);
            rankRepository.save(rank);
            personService.checkUserRank();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean update(RankDto rankDto) {
        try {
            Rank rank=rankRepository.findById(rankDto.getId()).get();
            if (rank== null) return false;
            rank.setName(rankDto.getName());
            rank.setPoints(rankDto.getPoints());
            rank.setDiscountPercentage(rankDto.getDiscountPercentage());
            rank.setPointsForCancelledOrders(rankDto.getPointsForCancelledOrders());
            rank.setPointsForCompletedOrder(rankDto.getPointsForCompletedOrder());
            rankRepository.save(rank);
            personService.checkUserRank();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(RankDto rankDto){
        try {
            Rank rank=rankRepository.findByName(rankDto.getName());
            if(rank==null)return false;
            if(personService.checkIfUserHasRank(rank.getId()))return false;
            rankRepository.delete(rank);
            return true;
        }catch (Exception e){
            return false;
        }

    }

}
