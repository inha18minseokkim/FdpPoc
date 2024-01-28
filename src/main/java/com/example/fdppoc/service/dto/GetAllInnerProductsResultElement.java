package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GetAllInnerProductsResultElement {
    private Long orderSequence;
    private Long id;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private Boolean isMainMaterial;
}
