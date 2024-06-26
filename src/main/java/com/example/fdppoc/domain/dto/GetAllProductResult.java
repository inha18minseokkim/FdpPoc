package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetAllProductResult {
    private String innerProductId;
    private String innerProductName;
    private String unitValueName;
    private Long currentPrice;
    private Long gapPrice;
    private Double gapPriceRatio;
    private Boolean isCustomerInterest;
    private Long innerCategoryId;
    private String innerCategoryName;
    private Boolean isRiseOrDecline;
    private Long clickCount;
}
