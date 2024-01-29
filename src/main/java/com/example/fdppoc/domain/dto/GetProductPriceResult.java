package com.example.fdppoc.domain.dto;

import com.example.fdppoc.code.BaseRange;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@Data
@ToString
public class GetProductPriceResult {
    private Long meanPrice;
    private Long minimumPrice;
    private Long maximumPrice;
    private BaseRange baseRange;
    private List<Long> priceList;
}
