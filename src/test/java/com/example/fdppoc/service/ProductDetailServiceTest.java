package com.example.fdppoc.service;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.UserGroupCode;
import com.example.fdppoc.service.dto.GetProductPriceIn;
import com.example.fdppoc.service.dto.GetProductPriceOut;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ProductDetailServiceTest {
    @Autowired
    ProductDetailService productDetailService;

    @Test
    void 상품리스트조회() {
        GetProductPriceIn input = GetProductPriceIn.builder()
                .baseDate("20240109")
                .targetProduct(BaseProduct.builder().id(1L).build())
                .rangeForLength(BaseRange.WEEK)
                .rangeForTag(BaseRange.DAY)
                .regionGroup(UserGroupCode.builder().id(52L).build())
                .build();
        GetProductPriceOut result = productDetailService.getProductPrice(input);
        log.info("실행결과 : {}",result);
    }
}