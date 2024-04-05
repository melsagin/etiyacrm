package com.etiyacrm.customerservice.services.dtos.responses.cities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCityPaginationResponse {
    private List<GetAllCityResponse> getAllCityResponse;
    private int size;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;
    private int totalPage;
}
