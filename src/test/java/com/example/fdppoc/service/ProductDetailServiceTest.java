package com.example.fdppoc.service;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.entity.UserGroupCode;
import com.example.fdppoc.repository.BaseProductRepository;
import com.example.fdppoc.repository.InnerProductRepository;
import com.example.fdppoc.service.dto.GetProductPriceIn;
import com.example.fdppoc.service.dto.GetProductPriceOut;
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
    ProductDetailService productDetailService;
    @Autowired
    InnerProductRepository innerProductRepository;

    @Test
    @Transactional
    @Rollback(value = true)
    void 상품가격리스트조회() {

        GetProductPriceIn input = GetProductPriceIn.builder()
                .baseDate("20240109")
                .targetProduct(innerProductRepository.findById(2L).get())
                .rangeForLength(BaseRange.WEEK)
                .rangeForTag(BaseRange.DAY)
                .regionGroup(UserGroupCode.builder().id(52L).build())
                .memberInfo(MemberInfo.builder().customerId("20160860").businessCode("001").build())
                .build();
        GetProductPriceOut result = productDetailService.getProductPrice(input);
        log.info("실행결과 : {}",result);
    }
}