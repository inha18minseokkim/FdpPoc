package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@Builder
public class GetInnerProductsWithFilterResult {
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
}
