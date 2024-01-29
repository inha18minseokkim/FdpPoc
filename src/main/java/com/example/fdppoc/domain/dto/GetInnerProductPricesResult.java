package com.example.fdppoc.domain.dto;

import com.example.fdppoc.domain.entity.InnerProduct;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetInnerProductPricesResult {
    private Double gapRatio;
    private Double gapPrice;
    private InnerProduct innerProduct;
    private Double currentPrice;
    private String baseDate;
    private Double averagePrice;

}
