package com.example.fdppoc.repository;

import com.example.fdppoc.infrastructure.interfaces.ProcessedPriceInfoReader;
import com.example.fdppoc.infrastructure.repository.InnerProductRepository;
import com.example.fdppoc.infrastructure.impl.InnerProductRepositoryImpl;
import com.example.fdppoc.infrastructure.repository.UserGroupCodeRepository;
import com.example.fdppoc.infrastructure.dto.GetPriceDiffInDto;
import com.example.fdppoc.infrastructure.dto.GetPriceDiffListInDto;
import com.example.fdppoc.infrastructure.dto.GetPriceDiffListOutDto;
import com.example.fdppoc.infrastructure.dto.GetPriceDiffOutDto;
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
class ProcessedPriceInfoReaderTest {
    @Autowired
    ProcessedPriceInfoReader priceInfoReader;
    @Autowired
    UserGroupCodeRepository userGroupCodeRepository;
    @Autowired
    InnerProductRepository innerProductRepository;
    @Autowired
    InnerProductRepositoryImpl innerProductRepositoryCustom;
    @Test
    void 범위내최대최소가격탐색() {
        GetPriceDiffOutDto minMaxPrice = priceInfoReader.getTodayAndWeeklyMeanPrice(
                GetPriceDiffInDto.builder()
                        .startDate("20240101")
                        .endDate("20240119")
                        .regionGroupCodeId("FDPREGN1101")
                        .targetInnerProductId("1004")
                        .build()
        );
        log.info("결과 : {}",minMaxPrice);
        Assertions.assertThat(minMaxPrice.getBaseDate()).isGreaterThanOrEqualTo(minMaxPrice.getPastDate());
    }
    @Test
    void  모든상품가격() {
        List<GetPriceDiffListOutDto> priceDiffList = priceInfoReader.getPriceDiffList(
                GetPriceDiffListInDto.builder().regionGroupCodeId("FDPREGN3100")
                        .startDate("20230112").endDate("20240119")
                        .build());
        log.info("중간결과 : {}",priceDiffList);
        Map<String, List<GetPriceDiffListOutDto>> collect = priceDiffList.parallelStream().collect(Collectors.groupingBy(element -> element.getInnerProductId()));
        collect.keySet().stream().map(collect::get).map(element -> {
            //기준일자
            Optional<GetPriceDiffListOutDto> baseDatePriceInfo = element.stream().max(Comparator.comparing(elem -> elem.getBaseDate().orElse("")));
            //상품정보
            String innerProductId = baseDatePriceInfo.get().getInnerProductId();
            //현재가격
            Double currentPrice = baseDatePriceInfo.get().getPrice().orElse(0.0);
            String baseDate = baseDatePriceInfo.get().getBaseDate().orElse("");
            //평균가격
            Double averagePrice = element.stream().collect(Collectors.averagingDouble(ele -> ele.getPrice().orElse(0.0)));
            return List.of(innerProductId,currentPrice,baseDate,averagePrice);
        }).forEach(
                element -> {
                        log.info("결과 : {}",element);
                }
        );
    }
    @Test
    void 최대날짜테스트(){
        String maxBaseDate = priceInfoReader.getMaxBaseDate("20240130");
        log.info("결과 : {}",maxBaseDate);
        Assertions.assertThat(maxBaseDate).isLessThanOrEqualTo("20240130");

    }
}