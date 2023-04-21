package com.techelevator.service;

import com.techelevator.model.zillowmodel.Result;
import com.techelevator.model.zillowmodel.ZillowBase;
import com.techelevator.model.zillowmodel.ZillowPropertyDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZillowServiceImp implements ZillowService {

    private static final String API_KEY = "X-RapidAPI-Key";

    private static final String API_VALUE = "e03436efc3msh747342dd29adb75p1fa8b0jsn64e91b8e1a7b";
    private static final String BASE_URL = "https://zillow56.p.rapidapi.com/search?location=";

    private final RestTemplate restTemplate;

    public ZillowServiceImp() {
        restTemplate = new RestTemplate();
    }
    @Override
    public Result[] getProperties(int zip, int minPrice, int maxPrice, int numberOfBedRooms, int numberOfBathRooms) {
        //Build search query with parameters in string concat
        String url = BASE_URL + zip + "&price_min=" + minPrice + "&price_max=" + maxPrice + "&beds_min" + numberOfBedRooms + "&baths_min=" + numberOfBathRooms;

        //Response Header encapsulates api key/value for api access
        HttpHeaders headers = new HttpHeaders();
        headers.set(API_KEY, API_VALUE);
        //v--Header and body = entity
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            ResponseEntity<ZillowBase> result;
            //RESTful Get request and exchange with API for response set to our result
            result = restTemplate.exchange(url, HttpMethod.GET, entity, ZillowBase.class);


            if (result.getBody() != null) {
                //Successful response body of properties is stored in our array.
                return result.getBody().getResults().toArray(new Result[0]);
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return new Result[]{};
    }

    @Override
    public List<ZillowPropertyDto> getZillowPropertyDtoList(int zip, int minPrice, int maxPrice, int numberOfBedRooms, int numberOfBathRooms) {
        List<ZillowPropertyDto> zillowPropertyDtoList = new ArrayList<>();
        //Result(remember props handler name issue) from zillow
        Result[] myProps = getProperties(zip, minPrice, maxPrice, numberOfBedRooms, numberOfBathRooms);

        //itterate through array
        for (Result p : myProps) {
            //mapped to prop dto
            ZillowPropertyDto zillowPropertyDto = mapToDto(p);
            //added to list
            zillowPropertyDtoList.add(zillowPropertyDto);
        }
        //returned to controller/whatever calls
        return zillowPropertyDtoList;
    }

    public ZillowPropertyDto mapToDto(Result result) {
        ZillowPropertyDto zillowPropertyDto = new ZillowPropertyDto();
        //dto--v    <--  //zillow--v
        zillowPropertyDto.setPrice(result.getPrice());
        zillowPropertyDto.setAddress(result.getStreetAddress());
        return zillowPropertyDto;
    }
}