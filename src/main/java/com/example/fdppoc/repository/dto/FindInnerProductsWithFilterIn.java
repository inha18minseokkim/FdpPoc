package com.example.fdppoc.repository.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class FindInnerProductsWithFilterIn {
    private String classificationCode;
    private String categoryCode;    //pd_ctgr_cd
}
