package com.example.fdppoc.infrastructure.dto;

import com.example.fdppoc.code.BaseRange;
import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindPriceListByGroupRegionCodeOut {
    private String baseDate;
    private String regionGroupId;
    private String innerProductId;
    private BaseRange baseRange;
    private Long price;
}
