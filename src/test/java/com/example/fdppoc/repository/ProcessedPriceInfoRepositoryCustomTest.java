package com.example.fdppoc.repository;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.infrastructure.repository.InnerProductRepository;
import com.example.fdppoc.infrastructure.repository.InnerProductRepositoryCustom;
import com.example.fdppoc.infrastructure.repository.ProcessedPriceInfoRepositoryCustom;
import com.example.fdppoc.infrastructure.repository.UserGroupCodeRepository;
import com.example.fdppoc.infrastructure.repository.dto.GetPriceDiffIn;
import com.example.fdppoc.infrastructure.repository.dto.GetPriceDiffListIn;
import com.example.fdppoc.infrastructure.repository.dto.GetPriceDiffListOut;
import com.example.fdppoc.infrastructure.repository.dto.GetPriceDiffOut;
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
    @Autowired
    InnerProductRepositoryCustom innerProductRepositoryCustom;
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
    void  모든상품가격() {
        List<GetPriceDiffListOut> priceDiffList = processedPriceInfoRepositoryCustom.getPriceDiffList(
                GetPriceDiffListIn.builder().regionGroup(userGroupCodeRepository.findById(52L).get())
                        .startDate("20240112").endDate("20240119")
                        .build());
        //log.info("중간결과 : {}",priceDiffList);
        Map<InnerProduct, List<GetPriceDiffListOut>> collect = priceDiffList.parallelStream().collect(Collectors.groupingBy(element -> element.getInnerProduct()));
        collect.keySet().stream().map(collect::get).map(element -> {
            //기준일자
            Optional<GetPriceDiffListOut> baseDatePriceInfo = element.stream().max(Comparator.comparing(elem -> elem.getBaseDate().orElse("")));
            //상품정보
            InnerProduct innerProduct = baseDatePriceInfo.get().getInnerProduct();
            //현재가격
            Double currentPrice = baseDatePriceInfo.get().getPrice().orElse(0.0);
            String baseDate = baseDatePriceInfo.get().getBaseDate().orElse("");
            //평균가격
            Double averagePrice = element.stream().collect(Collectors.averagingDouble(ele -> ele.getPrice().orElse(0.0)));
            return List.of(innerProduct,currentPrice,baseDate,averagePrice);
        }).forEach(
                element -> {
                        log.info("결과 : {}",element);
                }
        );
    }
}