package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.MemberInfo;
import com.example.fdppoc.entity.UserGroupCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class InsertProductHistoryIn {
    private BaseProduct baseProduct;
    private MemberInfo memberInfo;
    private UserGroupCode regionGroup;
}
