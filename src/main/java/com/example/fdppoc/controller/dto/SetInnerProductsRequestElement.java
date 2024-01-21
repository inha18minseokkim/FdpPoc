package com.example.fdppoc.controller.dto;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.InnerCategory;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@Data
@ToString
public class SetInnerProductsRequestElement {
    private Long id;
    private List<Long> baseProductIds;
    private Boolean isMainMaterial;
    private Boolean isAvailable;
    private Long innerCategoryId;
    private Long orderSequence;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private String seasonStartDate;
    private String seasonEndDate;
    private String rowStatus;
}
