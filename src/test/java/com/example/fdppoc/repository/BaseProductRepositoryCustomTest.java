package com.example.fdppoc.repository;

import com.example.fdppoc.repository.dto.FindBaseProductWithFilterIn;
import com.example.fdppoc.repository.dto.FindBaseProductWithFilterOut;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class BaseProductRepositoryCustomTest {
    @Autowired
    BaseProductRepositoryCustom baseProductRepositoryCustom;

    @Test
    @Transactional
    void 기본상품_카테고리코드_필터테스트() {
        FindBaseProductWithFilterIn in = FindBaseProductWithFilterIn.builder().categoryCode("100").build();
        List<FindBaseProductWithFilterOut> results = baseProductRepositoryCustom.findBaseProductWithFilter(in);
        log.info("실행 결과 : {}",results);
        Assertions.assertThat(results.get(0).getCategoryCode()).isEqualTo("100");
    }
    @Test
    @Transactional
    void 기본상품_상품코드_필터테스트() {
        FindBaseProductWithFilterIn in = FindBaseProductWithFilterIn.builder().itemCode("111").build();
        List<FindBaseProductWithFilterOut> results = baseProductRepositoryCustom.findBaseProductWithFilter(in);
        log.info("실행 결과 : {}",results);
        Assertions.assertThat(results.get(0).getItemCode()).isEqualTo("111");
    }
    @Test
    @Transactional
    void 기본상품_필터_and조건테스트() {
        FindBaseProductWithFilterIn in = FindBaseProductWithFilterIn.builder().itemCode("111").categoryCode("200").build();
        List<FindBaseProductWithFilterOut> results = baseProductRepositoryCustom.findBaseProductWithFilter(in);
        log.info("실행 결과 : {}",results);
        Assertions.assertThat(results.size()).isEqualTo(0);
    }
}