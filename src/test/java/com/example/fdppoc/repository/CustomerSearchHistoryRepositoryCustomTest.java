package com.example.fdppoc.repository;

import com.example.fdppoc.repository.dto.GetTopViewedInnerProductIn;
import com.example.fdppoc.repository.dto.GetTopViewedInnerProductOut;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@Transactional
class CustomerSearchHistoryRepositoryCustomTest {
    @Autowired
    CustomerSearchHistoryRepositoryCustom customerSearchHistoryRepositoryCustom;

    @Test
    void 범위시간내조회수출력() {
        List<GetTopViewedInnerProductOut> topViewedInnerProduct = customerSearchHistoryRepositoryCustom.getTopViewedInnerProduct(GetTopViewedInnerProductIn.builder()
                        .rangeHour(1)
                        .currentTime(LocalDateTime.now())
                .build());
        log.info("실행결과 : {}",topViewedInnerProduct);
    }
}