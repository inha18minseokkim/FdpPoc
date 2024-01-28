package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GetInnerProductsWithFilterCriteria {
    private Long innerCategoryId;
    private String categoryCode;
}
