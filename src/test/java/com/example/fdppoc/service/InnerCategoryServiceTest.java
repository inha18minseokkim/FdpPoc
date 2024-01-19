package com.example.fdppoc.service;

import com.example.fdppoc.service.dto.GetAllInnerProductsIn;
import com.example.fdppoc.service.dto.GetAllInnerProductsOut;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class InnerCategoryServiceTest {
    @Autowired
    InnerCategoryService innerCategoryService;

    @Test
    @Transactional
    void 가능한내부상품모두조회() {
        List<GetAllInnerProductsOut> allInnerProducts = innerCategoryService.getAllInnerProducts(GetAllInnerProductsIn.builder().build());
        log.info("결과 : {}",allInnerProducts);
    }
}