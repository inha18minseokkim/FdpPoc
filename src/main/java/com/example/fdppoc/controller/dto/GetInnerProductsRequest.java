package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class GetInnerProductsRequest {
    private Long innerCategoryId;
    private String categoryCode;    //pd_ctgr_cd
}
