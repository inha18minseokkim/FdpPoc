package com.example.fdppoc.service;

import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.impl.InnerProductServiceImpl;
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
    InnerProductServiceImpl innerProductService;
    @Test
    void 내부상품조회() {
        GetInnerProductsWithFilterCriteria in = GetInnerProductsWithFilterCriteria.builder()
                .categoryCode("100")
                .build();
        List<GetInnerProductsWithFilterResult> results = innerProductService.getInnerProductsWithFilter(in);
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
        List<SetInnerProductsCriteria> in = List.of(
                SetInnerProductsCriteria.builder()
                        .baseProductIds(List.of(202L))
                        .productName("소 안심")
                        .orderSequence(1L)
                        .rowStatus("C")
                        .isMainMaterial(true)
                        .isAvailable(true)
                        .isSeasonal(false)
                        .additionalDescription("소 안심")
                        .innerCategoryId(1L).build(),
                SetInnerProductsCriteria.builder()
                        .baseProductIds(List.of(203L))
                        .productName("돼지 앞다리")
                        .orderSequence(1L)
                        .rowStatus("C")
                        .isMainMaterial(false)
                        .isAvailable(true)
                        .isSeasonal(false)
                        .additionalDescription("돼지 앞다리")
                        .innerCategoryId(1L).build(),
                SetInnerProductsCriteria.builder()
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
        List<GetInnerProductsResult> innerProductList = innerProductService.getInnerProductList(GetInnerProductsCriteria.builder().isAvailable(true).build());
        log.info("실행 결과 : {}",innerProductList);
    }
    @Test
    void 내부상품리스트조회() {
        GetInnerProductsCriteria in = GetInnerProductsCriteria.builder().isAvailable(true).searchKeyword("구").build();
        List<GetInnerProductsResult> innerProductList = innerProductService.getInnerProductList(in);
        log.info("실행 결과 : {}", innerProductList);
        Assertions.assertThat(innerProductList.get(0).getProductName()).isEqualTo("고구마");
    }
    @Test
    void 내부상품리스트조회모두() {
        GetInnerProductsCriteria in = GetInnerProductsCriteria.builder().isAvailable(true).build();
        List<GetInnerProductsResult> innerProductList = innerProductService.getInnerProductList(in);
        log.info("실행 결과 : {}", innerProductList);

    }

}