package com.example.fdppoc.service;

import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.domain.interfaces.MemberService;
import com.example.fdppoc.infrastructure.repository.CustomerInterestProductRepository;
import com.example.fdppoc.infrastructure.repository.InnerProductRepository;
import com.example.fdppoc.infrastructure.repository.MemberInfoRepository;
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
    MemberService memberService;
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
        Optional<InnerProduct> rice = innerProductRepository.findById("1004");
        Optional<MemberInfo> member = memberInfoRepository.findById(102L);
        GetProductInterestCriteria input = GetProductInterestCriteria.builder()
                .targetProduct(rice.get())
                .customerId("20170860")
                .build();
        GetProductInterestResult first = memberService.getProductInterest(input);
        log.info("첫번째 : {}",first);

        SetProductInterestCriteria build = SetProductInterestCriteria.builder()
                .targetProduct(rice.get())
                .customerId("20170860")
                .isAvailable(true)
                .build();
        memberService.setProductInterest(build);
        //customerInterestProductRepository.flush();
        Assertions.assertThat(customerInterestProductRepository.getCustomerInterestProductByInnerProductAndMemberInfo(rice.get(),member.get()).get().getIsAvailable()).isEqualTo(true);
        //Assertions.assertThat(customerInterestProductService.getProductInterest(input).getIsAvailable()).isEqualTo(true);
    }
    @Test
    @Transactional
    @Rollback(true)
    void 관심상품해제조회() {
        Optional<InnerProduct> rice = innerProductRepository.findById("2");
        Optional<MemberInfo> member = memberInfoRepository.findById(102L);
        GetProductInterestCriteria input = GetProductInterestCriteria.builder()
                .targetProduct(rice.get())
                .customerId("20170860")
                .build();
        GetProductInterestResult first = memberService.getProductInterest(input);
        log.info("첫번째 : {}",first);

        SetProductInterestCriteria build = SetProductInterestCriteria.builder()
                .targetProduct(rice.get())
                .customerId("20170860")
                .isAvailable(false)
                .build();
        memberService.setProductInterest(build);
        //customerInterestProductRepository.flush();
        Assertions.assertThat(customerInterestProductRepository.getCustomerInterestProductByInnerProductAndMemberInfo(rice.get(),member.get()).get().getIsAvailable()).isEqualTo(false);
        //Assertions.assertThat(customerInterestProductService.getProductInterest(input).getIsAvailable()).isEqualTo(true);
    }

    @Test
    @Transactional
    void 관심상품리스트조회() {
        List<GetMemberInterestProductsResult> memberInterestProducts = memberService.getMemberInterestProducts(GetMemberInterestProductsCriteria.builder().customerId("20170860").build());
        log.info("결과 : {}", memberInterestProducts);
    }

}