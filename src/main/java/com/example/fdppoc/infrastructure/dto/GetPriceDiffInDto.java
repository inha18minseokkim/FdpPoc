package com.example.fdppoc.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetPriceDiffInDto {
    private String regionGroupId;
    private String targetInnerProductId;
    private String startDate;
    private String endDate;
}
