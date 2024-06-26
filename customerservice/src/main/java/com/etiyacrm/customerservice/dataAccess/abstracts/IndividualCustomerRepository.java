package com.etiyacrm.customerservice.dataAccess.abstracts;

import com.etiyacrm.customerservice.entities.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Long> {
    Optional<IndividualCustomer> findByNationalityId(String nationalityId);
}
