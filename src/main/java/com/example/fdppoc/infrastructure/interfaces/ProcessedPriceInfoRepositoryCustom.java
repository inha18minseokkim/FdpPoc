package com.example.fdppoc.infrastructure.interfaces;

import com.example.fdppoc.infrastructure.dto.*;

import java.util.List;

public interface ProcessedPriceInfoRepositoryCustom {
    List<FindPriceListByGroupRegionCodeOut> findPriceListByGroupRegionCode(FindPriceListByGroupRegionCodeIn in);
    GetPriceDiffOut getTodayAndWeeklyMeanPrice(GetPriceDiffIn in);
    List<GetPriceDiffListOut> getPriceDiffList(GetPriceDiffListIn in);
}
