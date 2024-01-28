package com.example.fdppoc.service;

import com.example.fdppoc.service.dto.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
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
    @Test
    @Transactional
    void 내부상품수정() {


    }
    @Test
    @Transactional
    @Rollback(value = true)
    void 내부상품삽입() {
        List<SetInnerProductsIn> in = List.of(
                SetInnerProductsIn.builder()
                        .baseProductIds(List.of(202L))
                        .productName("소 안심")
                        .orderSequence(1L)
                        .rowStatus("C")
                        .isMainMaterial(true)
                        .isAvailable(true)
                        .isSeasonal(false)
                        .additionalDescription("소 안심")
                        .innerCategoryId(1L).build(),
                SetInnerProductsIn.builder()
                        .baseProductIds(List.of(203L))
                        .productName("돼지 앞다리")
                        .orderSequence(1L)
                        .rowStatus("C")
                        .isMainMaterial(false)
                        .isAvailable(true)
                        .isSeasonal(false)
                        .additionalDescription("돼지 앞다리")
                        .innerCategoryId(1L).build(),
                SetInnerProductsIn.builder()
                        .baseProductIds(List.of(213L,214L,215L,216L))
                        .productName("배추")
                        .orderSequence(1L)
                        .rowStatus("C")
                        .isMainMaterial(false)
                        .isAvailable(true)
                        .isSeasonal(true)
                        .additionalDescription("배추")
                        .seasonStartDate("01")
                        .seasonEndDate("12")
                        .innerCategoryId(1L).build()
        );
        innerProductService.setInnerProducts(in);
        List<GetInnerProductListOut> innerProductList = innerProductService.getInnerProductList(GetInnerProductListIn.builder().isAvailable(true).build());
        log.info("실행 결과 : {}",innerProductList);
    }
    @Test
    void 내부상품리스트조회() {
        GetInnerProductListIn in = GetInnerProductListIn.builder().isAvailable(true).searchKeyword("구").build();
        List<GetInnerProductListOut> innerProductList = innerProductService.getInnerProductList(in);
        log.info("실행 결과 : {}", innerProductList);
        Assertions.assertThat(innerProductList.get(0).getProductName()).isEqualTo("고구마");
    }
    @Test
    void 내부상품리스트조회모두() {
        GetInnerProductListIn in = GetInnerProductListIn.builder().isAvailable(true).build();
        List<GetInnerProductListOut> innerProductList = innerProductService.getInnerProductList(in);
        log.info("실행 결과 : {}", innerProductList);

    }

}