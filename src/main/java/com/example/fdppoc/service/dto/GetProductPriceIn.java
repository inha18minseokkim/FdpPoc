package com.example.fdppoc.service.dto;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.UserGroupCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetProductPriceIn {
    private String baseDate;
    private BaseProduct targetProduct;
    private BaseRange findRange;
    private UserGroupCode regionGroup;
}
