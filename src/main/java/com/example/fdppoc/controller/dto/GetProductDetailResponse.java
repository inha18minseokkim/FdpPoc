package com.example.fdppoc.controller.dto;

import com.example.fdppoc.code.BaseRange;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Data
public class GetProductDetailResponse {
    private Long meanPrice;
    private Long minimumPrice;
    private Long maximumPrice;
    private BaseRange baseRange;
    private List<Long> priceList;
}
