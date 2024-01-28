package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.entity.UserGroupCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class InsertProductHistoryCriteria {
    private InnerProduct innerProduct;
    private MemberInfo memberInfo;
    private UserGroupCode regionGroup;
}
