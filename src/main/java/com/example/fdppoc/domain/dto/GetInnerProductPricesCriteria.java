package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class GetInnerProductPricesCriteria {
    private Long regionGroupId;
    private String startDate;
    private String endDate;
}
