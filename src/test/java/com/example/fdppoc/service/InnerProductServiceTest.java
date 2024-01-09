package com.example.fdppoc.service;

import com.example.fdppoc.service.dto.GetInnerProductsWithFilterIn;
import com.example.fdppoc.service.dto.GetInnerProductsWithFilterOut;
import com.example.fdppoc.service.dto.SetInnerProductsIn;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
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
    void 내부상품수정() {

    }
    @Test
    @Transactional
    void 내부상품삽입() {
        List<SetInnerProductsIn> in = List.of(SetInnerProductsIn.builder()
                        .baseProductId(2L)
                        .productName("콩")
                        .orderSequence(1L)
                        .rowStatus("C")
                        .isMainMaterial(false)
                        .isSeasonal(true)
                        .additionalDescription("국산콩")
                        .seasonStartDate("01")
                        .seasonEndDate("12")
                        .classificationCode("0001")
                .build());
        innerProductService.setInnerProducts(in);
        GetInnerProductsWithFilterIn input = GetInnerProductsWithFilterIn.builder().categoryCode("100").build();
        List<GetInnerProductsWithFilterOut> results = innerProductService.getInnerProductsWithFilter(input);
        log.info("조회 결과 : ",results);
        Assertions.assertThat(results.stream().map((element) -> element.getAdditionalDescription()))
                .contains("국산콩");

    }

}