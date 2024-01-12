package com.example.fdppoc.controller.dto;

import com.example.fdppoc.entity.BaseProduct;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class SetInnerProductsRequestElement {
    private Long id;
    private Long baseProductId;
    private Boolean isMainMaterial;
    private Boolean isAvailable;
    private String classificationCode;
    private Long orderSequence;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private String seasonStartDate;
    private String seasonEndDate;
    private String rowStatus;
}
