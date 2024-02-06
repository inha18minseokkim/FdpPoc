package com.example.fdppoc.infrastructure.dto;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.UserGroupCode;
import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindPriceListByGroupRegionCodeOut {
    private String baseDate;
    private String regionGroupCodeId;
    private String innerProductId;
    private BaseRange baseRange;
    private Long price;
}
