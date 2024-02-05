package com.example.fdppoc.domain.dto;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.domain.entity.UserGroupCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetProductPriceCriteria {
    private String baseDate;
    private String targetInnerProductId;
    private BaseRange rangeForLength;
    private BaseRange rangeForTag;
    private String regionGroupCodeId;
    private String customerId;
}
