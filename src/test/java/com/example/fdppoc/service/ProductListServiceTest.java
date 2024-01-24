package com.example.fdppoc.service;

import com.example.fdppoc.service.dto.GetPopularProductCriteria;
import com.example.fdppoc.service.dto.GetPopularProductResult;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductListServiceTest {
    @Autowired
    ProductListService productListService;

    @Test
    @Transactional
    void 시간내최근상품조회() {
        List<GetPopularProductResult> popularProduct = productListService.getPopularProduct(GetPopularProductCriteria.builder()
                        .baseDate("20240119")
                        .currentTime(LocalDateTime.now())
                        .rangeHour(24L)
                        .regionGroupId(152L)
                .build());
        log.info("실행 결과 : {}",popularProduct);
    }

}