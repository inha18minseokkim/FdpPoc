package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class GetInnerProductsRequest {
    private String classificationCode;
    private String categoryCode;    //pd_ctgr_cd
}
