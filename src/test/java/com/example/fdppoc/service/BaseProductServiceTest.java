package com.example.fdppoc.service;

import com.example.fdppoc.domain.impl.BaseProductServiceImpl;
import com.example.fdppoc.domain.dto.GetBaseCodesCriteria;
import com.example.fdppoc.domain.dto.GetBaseCodesResult;
import com.example.fdppoc.domain.dto.SetBaseCodesCriteria;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class BaseProductServiceTest {
    @Autowired
    BaseProductServiceImpl baseProductService;

    @Test
    @Transactional
    @Rollback(true)
    void 기본상품삽입() {
        List<SetBaseCodesCriteria> lists = new ArrayList<>();
        lists.add(SetBaseCodesCriteria.builder()
                        .categoryCode("600")
                        .itemCode("4301")
                        .kindCode("21")
                        .classCode("01")
                        .gradeCode("01")
                        .categoryName("축산물")
                        .itemName("소")
                        .kindName("안심(100g)")
                        .gradeName("상품")
                        .unitName("g")
                        .unitValue(100.0F)
                        .rowStatus("C")
                        .isAvailable(true)
                .build());
        baseProductService.setBaseCodes(lists);
        List<GetBaseCodesResult> baseCodes = baseProductService.getBaseCodes(GetBaseCodesCriteria.builder().build());
        log.info("결과 : {}", baseCodes);
    }

}