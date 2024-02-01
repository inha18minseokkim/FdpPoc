package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class GetInnerProductPricesCriteria {
    private String regionGroupId;
    private String startDate;
    private String endDate;
}
