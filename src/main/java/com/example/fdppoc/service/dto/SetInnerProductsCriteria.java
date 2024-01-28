package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class SetInnerProductsCriteria {
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
