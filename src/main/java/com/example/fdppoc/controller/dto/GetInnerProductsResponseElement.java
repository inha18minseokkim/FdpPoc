package com.example.fdppoc.controller.dto;

import com.example.fdppoc.entity.BaseProduct;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class GetInnerProductsResponseElement {
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
