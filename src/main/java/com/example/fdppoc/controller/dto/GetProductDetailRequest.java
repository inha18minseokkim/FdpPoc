package com.example.fdppoc.controller.dto;

import com.example.fdppoc.code.BaseRange;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetProductDetailRequest {
    private String baseDate;
    //private BaseProduct targetProduct;
    private BaseRange rangeForLength;
    //private UserGroupCode regionGroup;
    private String customerId;
}
