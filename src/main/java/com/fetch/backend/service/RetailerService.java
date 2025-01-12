package com.fetch.backend.service;

import com.fetch.backend.dto.Response;
import com.fetch.backend.dto.RetailerRequest;
import com.fetch.backend.entity.Item;
import com.fetch.backend.entity.Retailer;
import com.fetch.backend.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RetailerService {

    @Autowired
    private RetailerRepository retailerRepository;



    /**
     *
     * @param RetailerRequest - RetailerRequest Object
     *  @return responseId
     */
    public Response createRetailer(RetailerRequest retailerRequest) {
        Response response=new Response();
        Retailer retailer=new Retailer();
        retailer.setPurchaseDate(retailerRequest.getPurchaseDate());
        retailer.setPurchaseTime(LocalTime.parse(retailerRequest.getPurchaseTime()));
        retailer.setRetailerName(retailerRequest.getRetailer());
        retailer.setTotal(retailerRequest.getTotal());
        retailer.setItems(retailerRequest.getItems().stream().map((itemrequest)-> {
            Item item=new Item();
            item.setShortDescription(itemrequest.getShortDescription());
            item.setPrice(itemrequest.getPrice());
            return item;

        }).collect(Collectors.toList()));
        String uniqueID = UUID.randomUUID().toString();
        retailer.setResponse(uniqueID);

        int length_points=retailerRequest.getRetailer().replaceAll("[^a-zA-Z0-9]","").length();
        int dayPoints=(retailerRequest.getPurchaseDate().getDayOfMonth() %2!=0) ? 6: 0;
        int size=retailerRequest.getItems().size();
        int pairPoints=(size%2!=0? (size-1) * 5: size*5)/2;
        int items_multiple_3_points = retailerRequest.getItems().stream()
    .mapToInt(itemRequest -> {
        int length = itemRequest.getShortDescription().trim().length();
        if (length % 3 == 0) {
            BigDecimal price = itemRequest.getPrice().multiply(new BigDecimal("0.2"));
            return price.setScale(0, BigDecimal.ROUND_UP).intValue(); // Round up
        }
        return 0; // No points if length is not a multiple of 3
    })
    .sum();

        int time=Integer.parseInt(retailerRequest.getPurchaseTime().substring(0, 2));
        int time_points=(time==14 || time==15)?10:0;
        BigDecimal total = retailerRequest.getTotal();
        int roundDollarPoints = (total.stripTrailingZeros().scale() <= 0) ? 50 : 0;

        int multipleOf25Points = (total.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0) ? 25 : 0;

        int points=length_points+dayPoints+pairPoints+time_points+roundDollarPoints+multipleOf25Points+items_multiple_3_points;
   
        retailer.setPoints(points);
        retailerRepository.save(retailer);
        Map<String,String> responseMap=new HashMap<>();
        responseMap.put("id", uniqueID);
        response.setResponse(responseMap);
        return response;




    }

    /**
     *
     * @param responseId
     *  @return Points for given responseId
     */
    public ResponseEntity<Map<String,Integer>> getResponseById(String id) {
        
        int points=retailerRepository.getPointsByResponse(id);
        Map<String,Integer> responseMap=new HashMap<>();
        responseMap.put("points", points);
        return ResponseEntity.ok(responseMap);
       
    }
}
