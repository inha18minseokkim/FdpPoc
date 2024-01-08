package com.example.fdppoc.repository;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.repository.dto.FindInnerProductsWithFilterIn;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

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
    @Rollback(value = false)
    void 삽입_테스트() {
        Optional<BaseProduct> rice = baseProductRepository.findById(1L);
        InnerProduct innerProduct = InnerProduct.builder()
                .baseProduct(rice.orElseThrow())
                .isMainMaterial(true)
                .classificationCode("001")
                .orderSequence(1L)
                .additionalDescription("내부상품노출쌀")
                .isSeasonal(false)
                .build();
        log.info("BaseProduct 출력: {}",rice.get());
        log.info("InnerProduct 출력: {}",innerProduct);
        InnerProduct result = innerProductRepository.save(innerProduct);

        Assertions.assertThat(result.getBaseProduct())
                .isEqualTo(rice.get());

    }
    @Test
    void 동적쿼리_조회테스트(){
        FindInnerProductsWithFilterIn testInput = FindInnerProductsWithFilterIn
                .builder().categoryCode("100").build();
        innerProductRepositoryCustom.findInnerProductWithFilter(testInput);
    }
}