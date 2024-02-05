package com.example.fdppoc.domain.impl;

import com.example.fdppoc.domain.dto.GetDetailPriceCriteria;
import com.example.fdppoc.domain.dto.GetDetailPriceLegacyResult;
import com.example.fdppoc.domain.interfaces.ProductService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    ProductService productService;
    @Test
    @Transactional
    void 상세정보Legacy조회() {
        GetDetailPriceLegacyResult detailPriceLegacy = productService.getDetailPriceLegacy(GetDetailPriceCriteria.builder()
                .innerProductId("002")
                .customerId("20160860")
                .regionGroupId("")
                .baseDate("20240130")
                .build()
        );
        log.info("결과 : {}",detailPriceLegacy);
    }
}