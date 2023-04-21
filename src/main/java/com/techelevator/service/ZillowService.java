package com.techelevator.service;

import com.techelevator.model.zillowmodel.Result;
import com.techelevator.model.zillowmodel.ZillowPropertyDto;

import java.util.List;

public interface ZillowService {

    Result[] getProperties(int zip, int minPrice, int maxPrice, int numberOfBedRooms, int numberOfBathRooms);

    List<ZillowPropertyDto> getZillowPropertyDtoList(int zip, int minPrice, int maxPrice, int numberOfBedRooms, int numberOfBathRooms);
}
