package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.CharacteristicsDto;
import com.example.apotekasmilje.model.products.Characteristics;
import com.example.apotekasmilje.model.products.Product;

import java.util.ArrayList;
import java.util.List;

public class CharacteristicsMapper {
    public  Characteristics characteristicsDtoToCharacteristics(CharacteristicsDto characteristicsDto, Product product){
        return new Characteristics(characteristicsDto.getId(), characteristicsDto.getName(), product);
    }

    public List<Characteristics> characteristicsDtoToCharacteristics(List<CharacteristicsDto>characteristicsDtos,Product product){
        List<Characteristics> characteristics= new ArrayList<>();
        for(CharacteristicsDto characteristicsDto: characteristicsDtos)
            characteristics.add(characteristicsDtoToCharacteristics(characteristicsDto,product));
        return  characteristics;
    }



    public List<CharacteristicsDto>characteristicsToCharacteristicsDto(List<Characteristics> characteristics){
        List<CharacteristicsDto> characteristicsDtos = new ArrayList<>();
        for(Characteristics chara: characteristics){
            CharacteristicsDto characteristicsDto =
                    new CharacteristicsDto(chara.getId(),chara.getName());
            characteristicsDtos.add(characteristicsDto);
        }
        return characteristicsDtos;
    }
}
