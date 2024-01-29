package com.example.fdppoc.infrastructure.repository.dto;

import com.example.fdppoc.domain.entity.BaseProduct;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Data
public class FindInnerProductWithFilterOut {
    private Long id;
    private List<BaseProduct> baseProducts;
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
