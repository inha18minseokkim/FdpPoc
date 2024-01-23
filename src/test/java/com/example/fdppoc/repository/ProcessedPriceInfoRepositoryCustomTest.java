package com.example.fdppoc.repository;

import com.example.fdppoc.repository.dto.GetPriceDiffIn;
import com.example.fdppoc.repository.dto.GetPriceDiffOut;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        GetPriceDiffOut minMaxPrice = processedPriceInfoRepositoryCustom.getPriceDiff(
                GetPriceDiffIn.builder()
                        .startDate("20240101")
                        .endDate("20240119")
                        .regionGroup(userGroupCodeRepository.findById(52L).get())
                        .targetProduct(innerProductRepository.findById(1004L).get())
                        .build()
        );
        log.info("결과 : {}",minMaxPrice);
        Assertions.assertThat(minMaxPrice.getBaseDate()).isGreaterThanOrEqualTo(minMaxPrice.getPastDate());
    }

}