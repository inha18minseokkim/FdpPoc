package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.InnerProduct;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetPopularProductResult {
    private InnerProduct innerProduct;
    private Long count;
    private Long gapPrice;
    private Double gapRatio;
}
