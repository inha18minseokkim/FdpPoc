package com.example.fdppoc.domain.dto;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.MemberInfo;
import com.example.fdppoc.domain.entity.UserGroupCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class InsertProductHistoryCriteria {
    private String innerProductId;
    private Long memberInfoId;
    private String regionGroupCodeId;
}
