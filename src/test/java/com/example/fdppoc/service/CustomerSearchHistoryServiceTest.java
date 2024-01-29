package com.example.fdppoc.service;

import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.domain.entity.UserGroupCode;
import com.example.fdppoc.domain.interfaces.MemberService;
import com.example.fdppoc.infrastructure.repository.InnerProductRepository;
import com.example.fdppoc.infrastructure.repository.MemberInfoRepository;
import com.example.fdppoc.domain.dto.InsertProductHistoryCriteria;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SpringBootTest
@Slf4j
class CustomerSearchHistoryServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    InnerProductRepository innerProductRepository;
    @Autowired
    MemberInfoRepository memberInfoRepository;

    @Test
    @Transactional
    @Rollback(false)
    void 이력적재테스트 () {
        Optional<MemberInfo> tempMember = memberInfoRepository.findMemberInfoByCustomerIdAndBusinessCode("20160860", "001");
        memberService.insertProductHistory(InsertProductHistoryCriteria.builder()
                        .innerProduct(innerProductRepository.findById(1004L).get())
                        .regionGroup(UserGroupCode.builder().id(152L).build())
                        .memberInfo(tempMember.get())
                .build());

    }


}