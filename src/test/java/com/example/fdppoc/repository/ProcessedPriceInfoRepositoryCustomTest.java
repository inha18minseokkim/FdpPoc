package com.example.fdppoc.repository;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.repository.dto.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
@Transactional
class ProcessedPriceInfoRepositoryCustomTest {
    @Autowired
    ProcessedPriceInfoRepositoryCustom processedPriceInfoRepositoryCustom;
    @Autowired
    UserGroupCodeRepository userGroupCodeRepository;
    @Autowired
    InnerProductRepository innerProductRepository;
    @Test
    void 범위내최대최소가격탐색() {
        GetPriceDiffOut minMaxPrice = processedPriceInfoRepositoryCustom.getTodayAndWeeklyMeanPrice(
                GetPriceDiffIn.builder()
                        .startDate("20240101")
                        .endDate("20240119")
                        .regionGroup(userGroupCodeRepository.findById(152L).get())
                        .targetProduct(innerProductRepository.findById(1004L).get())
                        .build()
        );
        log.info("결과 : {}",minMaxPrice);
        Assertions.assertThat(minMaxPrice.getBaseDate()).isGreaterThanOrEqualTo(minMaxPrice.getPastDate());
    }
    @Test
    void 모든상품해시맵() {
        Map<Long, InnerProduct> allProduct = processedPriceInfoRepositoryCustom.getAllProduct(GetAllProductIn.builder().build());
        log.info("모든상품 : {}",allProduct);
    }
    @Test
    void  모든상품가격() {
        List<GetPriceDiffListOut> priceDiffList = processedPriceInfoRepositoryCustom.getPriceDiffList(
                GetPriceDiffListIn.builder().regionGroup(userGroupCodeRepository.findById(52L).get())
                        .startDate("20240112").endDate("20240119")
                        .build());
        Map<InnerProduct, List<GetPriceDiffListOut>> collect = priceDiffList.parallelStream().collect(Collectors.groupingBy(element -> element.getInnerProduct()));
        collect.keySet().stream().map(collect::get).map(element -> {
            //기준일자
            Optional<GetPriceDiffListOut> baseDatePriceInfo = element.stream().max(Comparator.comparing(GetPriceDiffListOut::getBaseDate));
            //현재가격
            Double currentPrice = baseDatePriceInfo.get().getPrice();
            String baseDate = baseDatePriceInfo.get().getBaseDate();
            //평균가격
            Double averagePrice = element.stream().collect(Collectors.averagingDouble(ele -> ele.getPrice()));
            return List.of(currentPrice,baseDate,averagePrice);
        }).forEach(
                element -> {
                        log.info("결과 : {}",element);
                }
        );
    }
}