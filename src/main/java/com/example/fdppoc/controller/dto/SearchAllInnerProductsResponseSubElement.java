package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class SearchAllInnerProductsResponseSubElement {
    private Long orderSequence;
    private Long id;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private Boolean isMainMaterial;
}
