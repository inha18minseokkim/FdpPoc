package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.InnerProduct;
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
