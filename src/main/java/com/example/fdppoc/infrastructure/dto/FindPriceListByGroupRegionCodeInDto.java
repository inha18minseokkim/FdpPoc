package com.example.fdppoc.infrastructure.dto;

import com.example.fdppoc.code.BaseRange;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindPriceListByGroupRegionCodeInDto {
    private String baseDate;
    private String targetInnerProductId;
    private BaseRange rangeForLength;
    private BaseRange rangeForTag;
    private String regionGroupId;
}
