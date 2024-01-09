package com.example.fdppoc.service;

import com.example.fdppoc.service.dto.GetInnerProductsWithFilterIn;
import com.example.fdppoc.service.dto.GetInnerProductsWithFilterOut;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class InnerProductServiceTest {
    @Autowired
    InnerProductService innerProductService;

    @Test
    void 내부상품조회() {
        GetInnerProductsWithFilterIn in = GetInnerProductsWithFilterIn.builder()
                .categoryCode("100")
                .build();
        List<GetInnerProductsWithFilterOut> results = innerProductService.getInnerProductsWithFilter(in);
        log.info("실행 결과 : " + results);
    }

}