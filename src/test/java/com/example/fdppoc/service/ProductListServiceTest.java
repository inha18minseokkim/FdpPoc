package com.example.fdppoc.service;

import com.example.fdppoc.domain.dto.GetAllProductCriteria;
import com.example.fdppoc.domain.dto.GetAllProductResult;
import com.example.fdppoc.domain.dto.GetPopularProductCriteria;
import com.example.fdppoc.domain.dto.GetPopularProductResult;
import com.example.fdppoc.domain.interfaces.ProductService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
class ProductListServiceTest {
    @Autowired
    ProductService productService;

    @Test
    @Transactional
    void 시간내최근상품조회() {
        List<GetPopularProductResult> popularProduct = productService.getPopularProduct(GetPopularProductCriteria.builder()
                        .baseDate("20240119")
                        .currentTime(LocalDateTime.now())
                        .rangeHour(24L)
                        .regionGroupId("")
                .build());
        log.info("실행 결과 : {}",popularProduct);
    }

    @Test
    @Transactional
    void legacy용모든상품조회() {
        List<GetAllProductResult> allProduct = productService.getAllProduct(GetAllProductCriteria.builder()
                .baseDate("20240119")
                .customerId("20170860")
                .regionGroupId("FDPREGN1101")
                .build());
        log.info("결과 : {}",allProduct);
    }
}