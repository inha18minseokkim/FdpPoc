package com.example.fdppoc.controller.dto;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.UserGroupCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetProductDetailIn {
    private String baseDate;
    //private BaseProduct targetProduct;
    private BaseRange rangeForLength;
    //private UserGroupCode regionGroup;
}
