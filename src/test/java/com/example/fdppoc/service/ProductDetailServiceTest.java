package com.example.fdppoc.service;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.domain.entity.UserGroupCode;
import com.example.fdppoc.domain.interfaces.ProductPriceService;
import com.example.fdppoc.infrastructure.repository.InnerProductRepository;
import com.example.fdppoc.domain.dto.GetProductPriceCriteria;
import com.example.fdppoc.domain.dto.GetProductPriceResult;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Slf4j
class ProductDetailServiceTest {
    @Autowired
    ProductPriceService productPriceService;
    @Autowired
    InnerProductRepository innerProductRepository;

    @Test
    @Transactional
    @Rollback(value = true)
    void 상품가격리스트조회() {

        GetProductPriceCriteria input = GetProductPriceCriteria.builder()
                .baseDate("20240119")
                .targetProduct(innerProductRepository.findById(1004L).get())
                .rangeForLength(BaseRange.MONTH)
                .rangeForTag(BaseRange.DAY)
                .regionGroup(UserGroupCode.builder().id(152L).build())
                .customerId("20170860")
                .build();
        GetProductPriceResult result = productPriceService.getProductPrice(input);
        log.info("실행결과 : {}",result);
    }
}