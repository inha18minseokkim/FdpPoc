package com.example.fdppoc.service;

import com.example.fdppoc.repository.ProcessedPriceInfoRepositoryCustom;
import com.example.fdppoc.repository.dto.FindPriceListByGroupRegionCodeOut;
import com.example.fdppoc.service.dto.GetProductPriceIn;
import com.example.fdppoc.service.dto.GetProductPriceOut;
import com.example.fdppoc.service.dto.InsertProductHistoryIn;
import com.example.fdppoc.service.mapper.ProductDetailServiceMapper;
import jakarta.transaction.Transactional;
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
    private final CustomerSearchHistoryService customerSearchHistoryService;
    private final ProductDetailServiceMapper mapper;
    @Transactional
    public GetProductPriceOut getProductPrice(GetProductPriceIn in){
        List<FindPriceListByGroupRegionCodeOut> dailyPrices = processedPriceInfoRepositoryCustom.findPriceListByGroupRegionCode(mapper.from(in));

        LongSummaryStatistics summary = dailyPrices.stream()
                .map(element -> element.getPrice()).mapToLong(Long::longValue).summaryStatistics();

        customerSearchHistoryService.insertProductHistory(InsertProductHistoryIn.builder()
                        .baseProduct(in.getTargetProduct())
                        .memberInfo(in.getMemberInfo())
                        .regionGroup(in.getRegionGroup())
                .build());

        return GetProductPriceOut.builder()
                .meanPrice(Math.round(summary.getAverage()))
                .maximumPrice(summary.getMax())
                .minimumPrice(summary.getMin())
                .priceList(dailyPrices.stream().map(element->element.getPrice()).collect(Collectors.toList()))
                .baseRange(in.getRangeForLength())
                .build();
    }
}
