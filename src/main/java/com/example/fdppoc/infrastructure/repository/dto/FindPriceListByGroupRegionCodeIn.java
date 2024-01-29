package com.example.fdppoc.infrastructure.repository.dto;

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
    private InnerProduct targetProduct;
    private BaseRange rangeForLength;
    private BaseRange rangeForTag;
    private UserGroupCode regionGroup;
}
