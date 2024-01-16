package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.MemberInfo;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GetProductInterestIn {
    private BaseProduct targetProduct;
    private MemberInfo memberInfo;
}
