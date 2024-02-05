package com.example.fdppoc.infrastructure.dto;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.UserGroupCode;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindPriceListByGroupRegionCodeIn {
    private String baseDate;
    private String targetInnerProductId;
    private BaseRange rangeForLength;
    private BaseRange rangeForTag;
    private String regionGroupCodeId;
}
