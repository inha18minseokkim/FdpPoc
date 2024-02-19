package com.example.fdppoc.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetPriceDiffListInDto {
    private String regionGroupId;
    private String startDate;
    private String endDate;
}
