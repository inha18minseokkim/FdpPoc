package com.example.fdppoc.service.dto;

import com.example.fdppoc.code.BaseRange;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetProductPriceOut {
    private Long meanPrice;
    private BaseRange baseRange;
    private List<Long> priceList;
}
