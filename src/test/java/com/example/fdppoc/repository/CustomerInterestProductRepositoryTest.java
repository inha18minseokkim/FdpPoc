package com.example.fdppoc.repository;

import com.example.fdppoc.infrastructure.repository.CustomerInterestProductRepository;
import com.example.fdppoc.infrastructure.repository.MemberInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class CustomerInterestProductRepositoryTest {
    @Autowired
    CustomerInterestProductRepository customerInterestProductRepository;
    @Autowired
    MemberInfoRepository memberInfoRepository;


    @Test
    void 관심상품리스트테스트() {
        memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode("20170860","001");
        //customerInterestProductRepository.findAllByMemberInfoAndIsAvailable()
    }

}