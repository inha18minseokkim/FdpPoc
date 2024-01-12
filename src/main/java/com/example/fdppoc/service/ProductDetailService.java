package com.example.fdppoc.service;

import com.example.fdppoc.repository.ProcessedPriceInfoRepositoryCustom;
import com.example.fdppoc.repository.dto.FindPriceListByGroupRegionCodeOut;
import com.example.fdppoc.service.dto.GetProductPriceIn;
import com.example.fdppoc.service.dto.GetProductPriceOut;
import com.example.fdppoc.service.mapper.ProductDetailServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductDetailService {
    private final ProcessedPriceInfoRepositoryCustom processedPriceInfoRepositoryCustom;
    private final ProductDetailServiceMapper mapper;
    public GetProductPriceOut getProductPrice(GetProductPriceIn in){
        List<FindPriceListByGroupRegionCodeOut> dailyPrices = processedPriceInfoRepositoryCustom.findPriceListByGroupRegionCode(mapper.from(in));

        LongSummaryStatistics summary = dailyPrices.stream()
                .map(element -> element.getPrice()).mapToLong(Long::longValue).summaryStatistics();

        return GetProductPriceOut.builder()
                .meanPrice(Math.round(summary.getAverage()))
                .maximumPrice(summary.getMax())
                .minimumPrice(summary.getMin())
                .priceList(dailyPrices.stream().map(element->element.getPrice()).collect(Collectors.toList()))
                .baseRange(in.getRangeForLength())
                .build();
    }
}
