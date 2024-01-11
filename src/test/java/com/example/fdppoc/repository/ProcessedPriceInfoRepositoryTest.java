package com.example.fdppoc.repository;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.UserGroupCode;
import com.example.fdppoc.repository.dto.FindPriceListByGroupRegionCodeIn;
import com.example.fdppoc.repository.dto.FindPriceListByGroupRegionCodeOut;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ProcessedPriceInfoRepositoryTest {
    @Autowired
    ProcessedPriceInfoRepositoryCustom processedPriceInfoRepositoryCustom;
    @Autowired
    BaseProductRepository baseProductRepository;
    @Autowired
    UserGroupCodeRepository userGroupCodeRepository;
    @Test
    @Transactional
    void 쌀일주일치조회() {
        Optional<BaseProduct> rice = baseProductRepository.findById(1L);
        log.info("해당 품목 조회 : {} ",rice.get());
        Optional<UserGroupCode> gyeongki = userGroupCodeRepository.findById(152L);
        log.info("다음 지역 조회 : {}",gyeongki.get());
        FindPriceListByGroupRegionCodeIn in = FindPriceListByGroupRegionCodeIn.builder()
                .baseDate("20240110")
                .findRange(BaseRange.Week)
                .targetProduct(rice.get())
                .regionGroup(gyeongki.get())
                .build();
        List<FindPriceListByGroupRegionCodeOut> results = processedPriceInfoRepositoryCustom.findPriceListByGroupRegionCode(in);
        log.info("실행결과 : {}",results);
        log.info("가격만 뽑아 : {} ",results.stream().map((element) -> element.getPrice()).collect(Collectors.toList()));
    }

}