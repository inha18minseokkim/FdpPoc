package com.example.fdppoc.controller.dto;

import com.example.fdppoc.code.BaseRange;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Data
public class GetProductDetailLegacyResponseElement {
    private BaseRange baseRange;
    private Long currentPrice;
    private Double minimumPrice;
    private Double maximumPrice;
    private Long listSize;
    private List<GetProductDetailLegacyResponseSubElement> list;

}
