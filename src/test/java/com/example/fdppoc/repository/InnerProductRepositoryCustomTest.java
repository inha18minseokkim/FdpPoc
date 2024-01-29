package com.example.fdppoc.repository;

import com.example.fdppoc.domain.entity.BaseProduct;
import com.example.fdppoc.domain.entity.InnerCategory;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.infrastructure.repository.BaseProductRepository;
import com.example.fdppoc.infrastructure.repository.InnerCategoryRepository;
import com.example.fdppoc.infrastructure.repository.InnerProductRepository;
import com.example.fdppoc.infrastructure.repository.InnerProductRepositoryCustom;
import com.example.fdppoc.infrastructure.repository.dto.FindInnerProductListIn;
import com.example.fdppoc.infrastructure.repository.dto.FindInnerProductWithFilterOut;
import com.example.fdppoc.infrastructure.repository.dto.FindInnerProductsWithFilterIn;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class InnerProductRepositoryCustomTest {
    @Autowired
    InnerProductRepositoryCustom innerProductRepositoryCustom;
    @Autowired
    InnerProductRepository innerProductRepository;
    @Autowired
    BaseProductRepository baseProductRepository;
    @Autowired
    InnerCategoryRepository innerCategoryRepository;

    @Test
    @Transactional
    @Rollback(value = true)
    void 삽입_테스트() {
        Optional<BaseProduct> rice = baseProductRepository.findById(52L);
        InnerProduct innerProduct = InnerProduct.builder()
                .baseProducts(List.of(rice.orElseThrow()))
                .isMainMaterial(true)
                .innerCategory(innerCategoryRepository.findById(2L).get())
                .orderSequence(1L)
                .additionalDescription("내부상품노출고구마")
                .productName("고구마")
                .isSeasonal(false)
                .build();
        log.info("BaseProduct 출력: {}",rice.get());
        log.info("InnerProduct 출력: {}",innerProduct);
        InnerProduct result = innerProductRepository.save(innerProduct);

        Assertions.assertThat(result.getBaseProducts().get(0))
                .isEqualTo(rice.get());

    }
    @Test
    @Transactional
    void 동적쿼리_조회테스트(){
        FindInnerProductsWithFilterIn testInput = FindInnerProductsWithFilterIn
                .builder().categoryCode("100").innerCategoryId(1L).build();
        List<FindInnerProductWithFilterOut> results = innerProductRepositoryCustom.findInnerProductWithFilter(testInput);
        log.info("실행 결과 : {}",results);
        Assertions.assertThat(results.get(0).getBaseProducts().get(0).getCategoryCode()).isEqualTo("100");
    }
    @Test
    @Transactional
    @Rollback(value = true)
    void 수정_테스트() {

        InnerProduct innerProduct = innerProductRepository.findById(2L).get();

        log.info("InnerProduct 출력: {}",innerProduct);
        InnerCategory tobeCategory = innerCategoryRepository.findById(2L).get();
        innerProduct.setInnerCategory(tobeCategory);
        InnerProduct result = innerProductRepository.save(innerProduct);
        log.info("InnerProduct 출력: {}",result);
        Assertions.assertThat(result.getInnerCategory())
                .isEqualTo(tobeCategory);

    }
    @Test
    @Transactional
    void 내부상품대고객리스트조회() {
        List<InnerProduct> singleList = innerProductRepositoryCustom.findInnerProductList(
                FindInnerProductListIn.builder().searchKeyword("구").isAvailable(true).build());
        log.info("출력 : {}",singleList);
        Assertions.assertThat(singleList.get(0).getProductName()).isEqualTo("고구마");
    }
}