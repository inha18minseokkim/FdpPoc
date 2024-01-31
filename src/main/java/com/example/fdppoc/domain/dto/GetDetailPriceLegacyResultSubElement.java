package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GetDetailPriceLegacyResultSubElement {
    private String baseDate;
    private Double price;
}
