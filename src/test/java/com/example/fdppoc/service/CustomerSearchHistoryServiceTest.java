package com.example.fdppoc.service;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.entity.UserGroupCode;
import com.example.fdppoc.repository.BaseProductRepository;
import com.example.fdppoc.service.dto.InsertProductHistoryIn;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CustomerSearchHistoryServiceTest {
    @Autowired
    CustomerSearchHistoryService customerSearchHistoryService;
    @Autowired
    BaseProductRepository baseProductRepository;

    @Test
    @Transactional
    @Rollback(false)
    void 이력적재테스트 () {

        customerSearchHistoryService.insertProductHistory(InsertProductHistoryIn.builder()
                        .baseProduct(baseProductRepository.findById(1L).get())
                        .regionGroup(UserGroupCode.builder().id(52L).build())
                        .memberInfo(MemberInfo.builder().id(20160860L).build())
                .build());

    }


}