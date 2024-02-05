package com.example.fdppoc.domain.dto;

import com.example.fdppoc.domain.entity.InnerProduct;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetPopularProductResult {
    private String innerProductId;
    private Long count;
    private Long gapPrice;
    private Double gapRatio;
}
