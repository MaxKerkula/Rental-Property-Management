package com.techelevator.controller;

import com.techelevator.model.zillowmodel.ZillowPropertyDto;
import com.techelevator.service.ZillowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ZillowController {
    private final ZillowService zillowService;


    public ZillowController(ZillowService zillowService) {
        this.zillowService = zillowService;
    }

    @RequestMapping(path = "/zillowProperties/location/{zip}/minPrice/{minPrice}/maxPrice/{maxPrice}/bedrooms/{numberOfBedRooms}/bathrooms/{numberOfBathRooms}", method = RequestMethod.GET)
    public List<ZillowPropertyDto> getZillowProperties(@PathVariable int zip, @PathVariable int minPrice, @PathVariable int maxPrice, @PathVariable int numberOfBedRooms, @PathVariable int numberOfBathRooms) {
        return zillowService.getZillowPropertyDtoList(zip, minPrice, maxPrice, numberOfBedRooms, numberOfBathRooms);
    }
}
