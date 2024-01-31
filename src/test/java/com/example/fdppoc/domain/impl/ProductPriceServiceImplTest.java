package com.example.fdppoc.domain.impl;

import com.example.fdppoc.domain.dto.GetDetailPriceCriteria;
import com.example.fdppoc.domain.dto.GetDetailPriceLegacyResult;
import com.example.fdppoc.domain.interfaces.ProductPriceService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ProductPriceServiceImplTest {
    @Autowired
    ProductPriceService productPriceService;
    @Test
    @Transactional
    void 상세정보Legacy조회() {
        GetDetailPriceLegacyResult detailPriceLegacy = productPriceService.getDetailPriceLegacy(GetDetailPriceCriteria.builder()
                .innerProductId(2L)
                .customerId("20160860")
                .regionGroupId(52L)
                .baseDate("20240130")
                .build()
        );
        log.info("결과 : {}",detailPriceLegacy);
    }
}