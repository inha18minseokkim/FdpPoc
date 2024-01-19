package com.example.fdppoc.repository.dto;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.InnerCategory;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class FindInnerProductWithFilterOut {
    private Long id;
    private BaseProduct baseProduct;
    private Boolean isMainMaterial;
    private Boolean isAvailable;
    private Long innerCategoryId;
    private Long orderSequence;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private String seasonStartDate;
    private String seasonEndDate;
}
