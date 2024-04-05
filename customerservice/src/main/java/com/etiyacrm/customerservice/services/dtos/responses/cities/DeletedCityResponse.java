package com.etiyacrm.customerservice.services.dtos.responses.cities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeletedCityResponse {
    private long id;
    private String name;
}
