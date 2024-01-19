package com.example.fdppoc.repository.dto;

import com.example.fdppoc.entity.InnerCategory;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class FindInnerProductsWithFilterIn {
    private Long innerCategoryId;
    private String categoryCode;    //pd_ctgr_cd
}
