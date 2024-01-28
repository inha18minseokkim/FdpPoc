package com.example.fdppoc.service;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.CustomerInterestProduct;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.repository.BaseProductRepository;
import com.example.fdppoc.repository.CustomerInterestProductRepository;
import com.example.fdppoc.repository.InnerProductRepository;
import com.example.fdppoc.repository.MemberInfoRepository;
import com.example.fdppoc.service.dto.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class CustomerInterestProductServiceTest {
    @Autowired
    CustomerInterestProductService customerInterestProductService;
    @Autowired
    InnerProductRepository innerProductRepository;
    @Autowired
    MemberInfoRepository memberInfoRepository;
    @Autowired
    CustomerInterestProductRepository customerInterestProductRepository;

    @Test
    @Transactional
    @Rollback(value = true)
    void 관심상품등록조회() {
        Optional<InnerProduct> rice = innerProductRepository.findById(1004L);
        Optional<MemberInfo> member = memberInfoRepository.findById(102L);
        GetProductInterestIn input = GetProductInterestIn.builder()
                .targetProduct(rice.get())
                .memberInfo(member.get())
                .build();
        GetProductInterestOut first = customerInterestProductService.getProductInterest(input);
        log.info("첫번째 : {}",first);

        SetProductInterestIn build = SetProductInterestIn.builder()
                .targetProduct(rice.get())
                .memberInfo(member.get())
                .isAvailable(true)
                .build();
        customerInterestProductService.setProductInterest(build);
        //customerInterestProductRepository.flush();
        Assertions.assertThat(customerInterestProductRepository.getCustomerInterestProductByInnerProductAndMemberInfo(rice.get(),member.get()).get().getIsAvailable()).isEqualTo(true);
        //Assertions.assertThat(customerInterestProductService.getProductInterest(input).getIsAvailable()).isEqualTo(true);
    }
    @Test
    @Transactional
    @Rollback(true)
    void 관심상품해제조회() {
        Optional<InnerProduct> rice = innerProductRepository.findById(2L);
        Optional<MemberInfo> member = memberInfoRepository.findById(102L);
        GetProductInterestIn input = GetProductInterestIn.builder()
                .targetProduct(rice.get())
                .memberInfo(member.get())
                .build();
        GetProductInterestOut first = customerInterestProductService.getProductInterest(input);
        log.info("첫번째 : {}",first);

        SetProductInterestIn build = SetProductInterestIn.builder()
                .targetProduct(rice.get())
                .memberInfo(member.get())
                .isAvailable(false)
                .build();
        customerInterestProductService.setProductInterest(build);
        //customerInterestProductRepository.flush();
        Assertions.assertThat(customerInterestProductRepository.getCustomerInterestProductByInnerProductAndMemberInfo(rice.get(),member.get()).get().getIsAvailable()).isEqualTo(false);
        //Assertions.assertThat(customerInterestProductService.getProductInterest(input).getIsAvailable()).isEqualTo(true);
    }

    @Test
    @Transactional
    void 관심상품리스트조회() {
        List<GetMemberInterestProductsOut> memberInterestProducts = customerInterestProductService.getMemberInterestProducts(GetMemberInterestProductsIn.builder().customerId("20170860").build());
        log.info("결과 : {}", memberInterestProducts);
    }

}