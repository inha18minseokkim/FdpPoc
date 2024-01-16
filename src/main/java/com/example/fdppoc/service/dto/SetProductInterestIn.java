package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.MemberInfo;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class SetProductInterestIn {
    private BaseProduct targetProduct;
    private MemberInfo memberInfo;
    private Boolean isAvailable;
}
