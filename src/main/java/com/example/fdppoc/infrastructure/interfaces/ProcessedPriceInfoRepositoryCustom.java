package com.example.fdppoc.infrastructure.interfaces;

import com.example.fdppoc.infrastructure.dto.*;

import java.util.List;

public interface ProcessedPriceInfoRepositoryCustom {
    List<FindPriceListByGroupRegionCodeOut> findPriceListByGroupRegionCode(FindPriceListByGroupRegionCodeInDto in);
    GetPriceDiffOutDto getTodayAndWeeklyMeanPrice(GetPriceDiffInDto in);
    List<GetPriceDiffListOutDto> getPriceDiffList(GetPriceDiffListInDto in);

    String getMaxBaseDate(String baseDate);
}
