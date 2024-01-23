package com.example.fdppoc.service;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.entity.UserGroupCode;
import com.example.fdppoc.repository.BaseProductRepository;
import com.example.fdppoc.repository.InnerProductRepository;
import com.example.fdppoc.repository.MemberInfoRepository;
import com.example.fdppoc.service.dto.InsertProductHistoryIn;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CustomerSearchHistoryServiceTest {
    @Autowired
    CustomerSearchHistoryService customerSearchHistoryService;
    @Autowired
    InnerProductRepository innerProductRepository;
    @Autowired
    MemberInfoRepository memberInfoRepository;

    @Test
    @Transactional
    @Rollback(false)
    void 이력적재테스트 () {
        Optional<MemberInfo> tempMember = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode("20160860", "001");
        customerSearchHistoryService.insertProductHistory(InsertProductHistoryIn.builder()
                        .innerProduct(innerProductRepository.findById(1004L).get())
                        .regionGroup(UserGroupCode.builder().id(152L).build())
                        .memberInfo(tempMember.get())
                .build());

    }


}