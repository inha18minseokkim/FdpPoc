package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class GetDetailPriceLegacyResult {
    private String baseDate;
    private Long regionGroupId;
    private Long innerProductId;
    private String innerProductName;
    private String unitName;
    private Double unitValue;
    private Double basePrice;
    private Double gapPrice;
    private Long listCount;
    private List<GetDetailPriceLegacyResultElement> list;
    private String weeklyForecastData;
    private String weeklyForcastUpDown;
    private Long otherRegionPriceInfoListCount;
    private List<Long> otherRegionPriceInfoList;
}
