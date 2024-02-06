package com.example.fdppoc.repository;

import com.example.fdppoc.infrastructure.impl.CustomerSearchHistoryRepositoryImpl;
import com.example.fdppoc.infrastructure.dto.GetTopViewedInnerProductInDto;
import com.example.fdppoc.infrastructure.dto.GetTopViewedInnerProductOutDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
class CustomerSearchHistoryRepositoryCustomTest {
    @Autowired
    CustomerSearchHistoryRepositoryImpl customerSearchHistoryRepositoryCustom;

    @Test
    void 범위시간내조회수출력() {
        List<GetTopViewedInnerProductOutDto> topViewedInnerProduct = customerSearchHistoryRepositoryCustom.getTopViewedInnerProduct(GetTopViewedInnerProductInDto.builder()
                        .rangeHour(1)
                        .currentTime(LocalDateTime.now())
                .build());
        log.info("실행결과 : {}",topViewedInnerProduct);
    }
}