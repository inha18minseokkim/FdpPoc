package com.example.fdppoc.repository;

import com.example.fdppoc.repository.dto.GetMinMaxPriceIn;
import com.example.fdppoc.repository.dto.GetMinMaxPriceOut;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProcessedPriceInfoRepositoryCustomTest {
    @Autowired
    ProcessedPriceInfoRepositoryCustom processedPriceInfoRepositoryCustom;
    @Autowired
    UserGroupCodeRepository userGroupCodeRepository;
    @Autowired
    InnerProductRepository innerProductRepository;
    @Test
    void 범위내최대최소가격탐색() {
        GetMinMaxPriceOut minMaxPrice = processedPriceInfoRepositoryCustom.getMinMaxPrice(
                GetMinMaxPriceIn.builder()
                        .startDate("20240101")
                        .endDate("20240119")
                        .regionGroup(userGroupCodeRepository.findById(152L).get())
                        .targetProduct(innerProductRepository.findById(1004L).get())
                        .build()
        );
        log.info("결과 : {}",minMaxPrice);
        Assertions.assertThat(minMaxPrice.getMaxPrice()).isGreaterThan(minMaxPrice.getMinPrice());
    }

}