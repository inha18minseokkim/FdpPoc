package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetAllProductResult {
    private Long innerProductId;
    private String innerProductName;
    private String unitValueName;
    private Long currentPrice;
    private Long gapPrice;
    private Double gapPriceRatio;
    private Boolean isCustomerInterest;
    private Long innerCategoryId;
    private String innerCategoryName;
    private Boolean isRiseOrDecline;
    private String clickCount;
}
