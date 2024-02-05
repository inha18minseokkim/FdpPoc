package com.example.fdppoc.domain.dto;

import com.example.fdppoc.domain.entity.InnerProduct;
import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GetInnerProductPricesResult {
    private Double gapRatio;
    private Double gapPrice;
    private String innerProductId;
    private Double currentPrice;
    private String baseDate;
    private Double averagePrice;

}
