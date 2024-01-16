package com.example.fdppoc.service;

import com.example.fdppoc.entity.CustomerInterestProduct;
import com.example.fdppoc.repository.CustomerInterestProductRepository;
import com.example.fdppoc.service.dto.GetProductInterestIn;
import com.example.fdppoc.service.dto.GetProductInterestOut;
import com.example.fdppoc.service.dto.SetProductInterestIn;
import com.example.fdppoc.service.mapper.CustomerInterestProductServiceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerInterestProductService {
    private final CustomerInterestProductRepository customerInterestProductRepository;
    private final CustomerInterestProductServiceMapper mapper;

    @Transactional
    public GetProductInterestOut getProductInterest(GetProductInterestIn in){
        Optional<CustomerInterestProduct> result = customerInterestProductRepository.getCustomerInterestProductByBaseProductAndMemberInfo(in.getTargetProduct(), in.getMemberInfo());

        return GetProductInterestOut.builder().isAvailable(result.isEmpty()?false:result.get().getIsAvailable())
                .build();
    }
    @Transactional
    public void setProductInterest(SetProductInterestIn in){
        log.info("setProductInterest : {}",in);
        Optional<CustomerInterestProduct> result = customerInterestProductRepository
                .getCustomerInterestProductByBaseProductAndMemberInfo(in.getTargetProduct(), in.getMemberInfo());

        CustomerInterestProduct customerInterestProduct = result.orElseGet(() ->
                CustomerInterestProduct.builder()
                        .baseProduct(in.getTargetProduct())
                        .memberInfo(in.getMemberInfo())
                        .isAvailable(in.getIsAvailable())
                        .build()
        );

        customerInterestProduct.setIsAvailable(in.getIsAvailable());
        customerInterestProductRepository.save(customerInterestProduct);
    }
}
