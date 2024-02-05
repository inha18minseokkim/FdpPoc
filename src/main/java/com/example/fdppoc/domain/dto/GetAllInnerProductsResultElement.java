package com.example.fdppoc.domain.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAllInnerProductsResultElement {
    private Long orderSequence;
    private Long id;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private Boolean isMainMaterial;
}
