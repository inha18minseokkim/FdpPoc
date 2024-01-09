package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.BaseProduct;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class GetInnerProductsWithFilterOut {
    private Long id;
    private Long baseProductId;
    private Boolean isMainMaterial;
    private String classificationCode;
    private Long orderSequence;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private String seasonStartDate;
    private String seasonEndDate;
}
