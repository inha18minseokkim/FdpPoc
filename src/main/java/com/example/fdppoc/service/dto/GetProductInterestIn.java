package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.entity.MemberInfo;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GetProductInterestIn {
    private InnerProduct targetProduct;
    private MemberInfo memberInfo;
}
