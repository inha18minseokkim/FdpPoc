package com.example.fdppoc.domain.dto;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.MemberInfo;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GetProductInterestCriteria {
    private InnerProduct targetProduct;
    private String customerId;
}
