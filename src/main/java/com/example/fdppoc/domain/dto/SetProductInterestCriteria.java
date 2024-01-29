package com.example.fdppoc.domain.dto;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.MemberInfo;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class SetProductInterestCriteria {
    private InnerProduct targetProduct;
    private String customerId;
    private Boolean isAvailable;
}
