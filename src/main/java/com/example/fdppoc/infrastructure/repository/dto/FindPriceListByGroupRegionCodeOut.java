package com.example.fdppoc.infrastructure.repository.dto;

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
    private UserGroupCode regionGroupInfo;
    private InnerProduct innerProduct;
    private BaseRange baseRange;
    private Long price;
}
