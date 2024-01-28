package com.example.fdppoc.service;

import com.example.fdppoc.entity.CustomerInterestProduct;
import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.repository.CustomerInterestProductRepository;
import com.example.fdppoc.repository.MemberInfoRepository;
import com.example.fdppoc.service.dto.*;
import com.example.fdppoc.service.mapper.CustomerInterestProductServiceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerInterestProductService {
    private final CustomerInterestProductRepository customerInterestProductRepository;
    private final MemberService memberService;
    private final CustomerInterestProductServiceMapper mapper;

    @Transactional
    public GetProductInterestOut getProductInterest(GetProductInterestIn in){
        Optional<CustomerInterestProduct> result = customerInterestProductRepository.getCustomerInterestProductByInnerProductAndMemberInfo(in.getTargetProduct(), in.getMemberInfo());

        return GetProductInterestOut.builder().isAvailable(result.isEmpty()?false:result.get().getIsAvailable())
                .build();
    }
    @Transactional
    public void setProductInterest(SetProductInterestIn in){
        log.info("setProductInterest : {}",in);
        Optional<CustomerInterestProduct> result = customerInterestProductRepository
                .getCustomerInterestProductByInnerProductAndMemberInfo(in.getTargetProduct(), in.getMemberInfo());

        CustomerInterestProduct customerInterestProduct = result.orElseGet(() ->
                CustomerInterestProduct.builder()
                        .innerProduct(in.getTargetProduct())
                        .memberInfo(in.getMemberInfo())
                        .isAvailable(in.getIsAvailable())
                        .build()
        );

        customerInterestProduct.setIsAvailable(in.getIsAvailable());
        customerInterestProductRepository.save(customerInterestProduct);
    }
    @Transactional
    public List<GetMemberInterestProductsOut> getMemberInterestProducts(GetMemberInterestProductsIn in) {
        GetMemberOut member = memberService.getMember(GetMemberIn.builder().customerId(in.getCustomerId()).businessCode("001").build());
        List<CustomerInterestProduct> allProduct = customerInterestProductRepository.findAllByMemberInfoAndIsAvailable(member.getMemberInfo().get(), true);
        return allProduct.stream().map(element -> mapper.from(element)).collect(Collectors.toList());
    }
}
