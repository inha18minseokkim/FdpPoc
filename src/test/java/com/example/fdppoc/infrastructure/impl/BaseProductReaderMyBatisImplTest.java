package com.example.fdppoc.infrastructure.impl;

import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterInDto;
import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterOutDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class BaseProductReaderMyBatisImplTest {
    @Autowired
    BaseProductReaderMyBatisImpl myBatisImpl;
    @Autowired
    BaseProductReaderImpl jpaImpl;
    @Test
    void 입출력테스트() {

        List<FindBaseProductWithFilterOutDto> results = myBatisImpl.findBaseProductWithFilter(FindBaseProductWithFilterInDto
                .builder()
                        .categoryCode("100")
                .build());
        log.info("결과 : {}" ,results);
        Assertions.assertThat(results.stream().map(element->element.getCategoryCode()).toList())
                .doesNotContain("200");
    }
    @Test
    void 비교테스트() {
        List<FindBaseProductWithFilterOutDto> myBatisResults = myBatisImpl.findBaseProductWithFilter(FindBaseProductWithFilterInDto
                .builder()
                .categoryCode("100")
                .build());
        List<FindBaseProductWithFilterOutDto> jpaResults = jpaImpl.findBaseProductWithFilter(FindBaseProductWithFilterInDto
                .builder()
                .categoryCode("100")
                .build());
        Assertions.assertThat(myBatisResults).isEqualTo(jpaResults);

    }

}