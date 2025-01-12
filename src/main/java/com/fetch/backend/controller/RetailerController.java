package com.fetch.backend.controller;

import com.fetch.backend.dto.Response;
import com.fetch.backend.dto.RetailerRequest;
import com.fetch.backend.service.RetailerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
@Tag(
        name = "Retailer Controller",
        description = "Fetch Assesment REST APIs For POST and GET of reciept details"
)
public class RetailerController {


    private final RetailerService retailerService;

    public RetailerController(RetailerService retailerService) {
        this.retailerService = retailerService;
    }

     @Operation(
            summary = "Post REST API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            )
    }
    )

    @PostMapping("/process")
    public ResponseEntity<Map<String,String>> createRetailer(@RequestBody RetailerRequest retailerRequest) {
        Response response = retailerService.createRetailer(retailerRequest);
        return ResponseEntity.ok(response.getResponse());
    }

    @Operation(
        summary = "Get  REST API "
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        )
}
)
    @GetMapping("/{id}/points")
    public ResponseEntity<Map<String,Integer>> getResponse(@PathVariable("id") String id){
       return retailerService.getResponseById(id);
        
    }
}

