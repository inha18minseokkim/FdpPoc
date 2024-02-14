package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class GetInnerProductsResult {
    private Long orderSequence;
    private String id;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private Boolean isMainMaterial;
}
