package com.etiyacrm.customerservice.services.dtos.responses.individualCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeletedIndividualCustomerResponse {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String gender;
    private String motherName;
    private String fatherName;
    private String nationalityId;
    private LocalDate birthDate;
}
