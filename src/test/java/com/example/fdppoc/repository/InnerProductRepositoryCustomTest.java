package com.example.fdppoc.repository;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.repository.dto.FindInnerProductWithFilterOut;
import com.example.fdppoc.repository.dto.FindInnerProductsWithFilterIn;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class InnerProductRepositoryCustomTest {
    @Autowired
    InnerProductRepositoryCustom innerProductRepositoryCustom;
    @Autowired
    InnerProductRepository innerProductRepository;
    @Autowired
    BaseProductRepository baseProductRepository;

    @Test
    @Transactional
    @Rollback(value = true)
    void 삽입_테스트() {
        Optional<BaseProduct> rice = baseProductRepository.findById(52L);
        InnerProduct innerProduct = InnerProduct.builder()
                .baseProduct(rice.orElseThrow())
                .isMainMaterial(true)
                .classificationCode("003")
                .orderSequence(1L)
                .additionalDescription("내부상품노출고구마")
                .productName("고구마")
                .isSeasonal(false)
                .build();
        log.info("BaseProduct 출력: {}",rice.get());
        log.info("InnerProduct 출력: {}",innerProduct);
        InnerProduct result = innerProductRepository.save(innerProduct);

        Assertions.assertThat(result.getBaseProduct())
                .isEqualTo(rice.get());

    }
    @Test
    @Transactional
    void 동적쿼리_조회테스트(){
        FindInnerProductsWithFilterIn testInput = FindInnerProductsWithFilterIn
                .builder().categoryCode("100").classificationCode("001").build();
        List<FindInnerProductWithFilterOut> results = innerProductRepositoryCustom.findInnerProductWithFilter(testInput);
        log.info("실행 결과 : {}",results);
        Assertions.assertThat(results.get(0).getBaseProduct().getCategoryCode()).isEqualTo("100");
    }
    @Test
    @Transactional
    @Rollback(value = true)
    void 수정_테스트() {
        Optional<BaseProduct> rice = baseProductRepository.findById(52L);
        InnerProduct innerProduct = InnerProduct.builder()
                .baseProduct(rice.orElseThrow())
                .isMainMaterial(true)
                .classificationCode("003")
                .orderSequence(1L)
                .additionalDescription("내부상품노출고구마")
                .productName("고구마")
                .isSeasonal(false)
                .build();
        log.info("BaseProduct 출력: {}",rice.get());
        log.info("InnerProduct 출력: {}",innerProduct);
        InnerProduct result = innerProductRepository.save(innerProduct);

        Assertions.assertThat(result.getClassificationCode())
                .isEqualTo("003");

    }
    @Test
    void 내부상품대고객리스트조회() {
        List<InnerProduct> singleRice = innerProductRepository.findAllByIsAvailableAndClassificationCodeAndProductNameMatchesRegex(true, "001", "쌀");
        log.info("출력 : {}",singleRice);
    }
}