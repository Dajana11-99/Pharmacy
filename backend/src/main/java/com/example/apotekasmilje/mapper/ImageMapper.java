package com.example.apotekasmilje.mapper;

import com.example.apotekasmilje.dto.ImageDto;
import com.example.apotekasmilje.model.products.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageMapper {
    public ImageDto imageToImageDto(Image image){
        return new ImageDto(image.getId(),image.getUrl());
    }

    public Image imageDtoToImage(ImageDto imageDto){return  new Image(imageDto.getId(),imageDto.getUrl());}
    public List<ImageDto> imagesToImageDtos(List<Image> images){
        List<ImageDto> imageDtos=new ArrayList<>();
        for(Image image:images)
            imageDtos.add(imageToImageDto(image));
        return  imageDtos;
    }

    public List<Image> imageDtosToImages(List<ImageDto> imageDtos){
        List<Image> images = new ArrayList<>();
        for(ImageDto imageDto:imageDtos)
            images.add(imageDtoToImage(imageDto));
        return images;
    }
}
