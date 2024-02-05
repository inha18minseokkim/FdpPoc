package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class GetProductDetailLegacyResponse {
    private String baseDate;
    private String regionGroupId;
    private String innerProductId;
    private String innerProductName;
    private String unitName;
    private Long unitValue;
    private Double basePrice;
    private Double gapPrice;
    private Double listCount;
    private List<GetProductDetailLegacyResponseElement> list;
    private String weeklyForecastData;
    private String weeklyForcastUpDown;
    private Long otherRegionPriceInfoListCount;
    private List<Long> otherRegionPriceInfoList;
}
