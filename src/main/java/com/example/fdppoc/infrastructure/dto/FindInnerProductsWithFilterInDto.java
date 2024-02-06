package com.example.fdppoc.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class FindInnerProductsWithFilterInDto {
    private Long innerCategoryId;
    private String categoryCode;    //pd_ctgr_cd
}
