package com.etiyacrm.customerservice.services.concretes;

import com.etiyacrm.customerservice.mappers.IndividualCustomerMapper;
import com.etiyacrm.customerservice.services.abstracts.IndividualCustomerService;
import com.etiyacrm.customerservice.services.dtos.requests.individualCustomers.CreateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.requests.individualCustomers.UpdateIndividualCustomerRequest;
import com.etiyacrm.customerservice.services.dtos.responses.individualCustomers.*;
import com.etiyacrm.customerservice.dataAccess.abstracts.IndividualCustomerRepository;
import com.etiyacrm.customerservice.entities.Customer;
import com.etiyacrm.customerservice.entities.IndividualCustomer;
import com.etiyacrm.customerservice.services.rules.IndividualCustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    private IndividualCustomerRepository individualCustomerRepository;
    private IndividualCustomerBusinessRules individualCustomerBusinessRules;
    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        individualCustomerBusinessRules.individualCustomerNationalityIdCannotBeDuplicated(createIndividualCustomerRequest.getNationalityId());

        Customer customer = new Customer();
        customer.setEmail(createIndividualCustomerRequest.getEmail());
        customer.setCreatedDate(LocalDateTime.now());

        IndividualCustomer individualCustomer =
                IndividualCustomerMapper.INSTANCE.individualCustomerFromCreateIndividualCustomerRequest(createIndividualCustomerRequest);
        individualCustomer.setCustomer(customer);

        IndividualCustomer createdCustomer = individualCustomerRepository.save(individualCustomer);

        CreatedIndividualCustomerResponse createdIndividualCustomerResponse =
                IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdCustomer);
        return createdIndividualCustomerResponse;
    }

    @Override
    public List<GetAllIndividualCustomerResponse> findAll() {
        List<IndividualCustomer> allIndividualCustomers = individualCustomerRepository.findAll();
        List<GetAllIndividualCustomerResponse> getAllIndividualCustomerResponses = allIndividualCustomers.stream()
                .filter(individualCustomer -> individualCustomer.getCustomer().getDeletedDate() == null)
                .map(individualCustomer ->
                        IndividualCustomerMapper.INSTANCE
                                .getAllIndividualCustomerResponseFromIndividualCustomer(individualCustomer)).collect(Collectors.toList());
        return getAllIndividualCustomerResponses;
    }

    @Override
    public GetIndividualCustomerResponse findById(long id) {
        individualCustomerBusinessRules.individualCustomerIdMustExist(id);
//        individualCustomerBusinessRules.deletedIndividualCustomer(id);

        IndividualCustomer foundIndividualCustomer = individualCustomerRepository.findById(id).get();

        GetIndividualCustomerResponse getIndividualCustomerResponse =
                IndividualCustomerMapper.INSTANCE.getIndividualCustomerFromIndividualCustomer(foundIndividualCustomer);
        return getIndividualCustomerResponse;
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, long id) {
//        individualCustomerBusinessRules.deletedIndividualCustomer(id);
        individualCustomerBusinessRules.individualCustomerIdMustExist(id);
        individualCustomerBusinessRules.individualCustomerNationalityIdCannotBeDuplicated(updateIndividualCustomerRequest.getNationalityId());
        IndividualCustomer foundIndividualCustomer = individualCustomerRepository.findById(id).get();
        IndividualCustomer individualCustomer =
                IndividualCustomerMapper.INSTANCE.individualCustomerFromUpdateIndividualCustomerRequest(updateIndividualCustomerRequest);

        individualCustomer.setId(id);
        individualCustomer.setCustomer(foundIndividualCustomer.getCustomer());
        individualCustomer.getCustomer().setEmail(updateIndividualCustomerRequest.getEmail());
        individualCustomer.getCustomer().setUpdatedDate(LocalDateTime.now());
        IndividualCustomer updatedIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        UpdatedIndividualCustomerResponse updatedIndividualCustomerResponse =
                IndividualCustomerMapper.INSTANCE.updatedIndividualCustomerResponseFromIndividualCustomer(updatedIndividualCustomer);

        return updatedIndividualCustomerResponse;
    }

    @Override
    public DeletedIndividualCustomerResponse delete(long id) {
        individualCustomerBusinessRules.individualCustomerIdMustExist(id);
//        individualCustomerBusinessRules.deletedIndividualCustomer(id);

        IndividualCustomer foundIndividualCustomer = individualCustomerRepository.findById(id).get();
        foundIndividualCustomer.getCustomer().setId(id);
        foundIndividualCustomer.getCustomer().setDeletedDate(LocalDateTime.now());

        IndividualCustomer deletedIndividualCustomer = individualCustomerRepository.save(foundIndividualCustomer);

        DeletedIndividualCustomerResponse deletedIndividualCustomerResponse =
                IndividualCustomerMapper.INSTANCE.deleteIndividualCustomerResponseFromIndividualCustomer(deletedIndividualCustomer);
        return deletedIndividualCustomerResponse;
    }
}