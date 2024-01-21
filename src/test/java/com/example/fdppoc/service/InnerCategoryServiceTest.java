package com.example.fdppoc.service;

import com.example.fdppoc.service.dto.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class InnerCategoryServiceTest {
    @Autowired
    InnerCategoryService innerCategoryService;

    @Test

    void 가능한내부상품모두조회() {
        List<GetAllInnerProductsOut> allInnerProducts = innerCategoryService.getAllInnerProducts(GetAllInnerProductsIn.builder().build());
        log.info("결과 : {}",allInnerProducts);
    }
    @Test
    void 모든내부카테고리조회() {
        List<GetAllInnerCategoryOut> allInnerCategory = innerCategoryService.getAllInnerCategory(GetAllInnerCategoryIn.builder().build());
        log.info("결과 : {}",allInnerCategory);

    }

    @Test
    @Rollback(false)
    void 카테고리수정() {
        innerCategoryService.setInnerCategory(List.of(
                SetInnerCategoryIn.builder()
                        .innerCategoryName("무야호야아아")
                        .additionalDescription("축산물")
                        .isAvailable(true)
                        .orderSequence(1L)
                        .rowStatus("C")
                        .build()
                ,SetInnerCategoryIn.builder()
                        .innerCategoryName("무야호야아아")
                        .additionalDescription("채소")
                        .isAvailable(true)
                        .orderSequence(1L)
                        .rowStatus("C")
                        .build()
                ,SetInnerCategoryIn.builder()
                        .innerCategoryName("무야호야아아")
                        .additionalDescription("수산물")
                        .isAvailable(true)
                        .orderSequence(1L)
                        .rowStatus("C")
                        .build()
        ));
    }
}