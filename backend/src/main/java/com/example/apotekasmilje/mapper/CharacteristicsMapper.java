package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.CharacteristicsDto;
import com.example.apotekasmilje.model.products.Characteristics;

import java.util.ArrayList;
import java.util.List;

public class CharacteristicsMapper {
    public  Characteristics characteristicsDtoToCharacteristics(CharacteristicsDto characteristicsDto){
        return new Characteristics(characteristicsDto.getId(), characteristicsDto.getName(), null);
    }

    public List<Characteristics> characteristicsDtoToCharacteristics(List<CharacteristicsDto>characteristicsDtos){
        List<Characteristics> characteristics= new ArrayList<>();
        for(CharacteristicsDto characteristicsDto: characteristicsDtos)
            characteristics.add(characteristicsDtoToCharacteristics(characteristicsDto));
        return  characteristics;
    }
}
