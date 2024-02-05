package com.example.fdppoc.domain.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAllInnerProductsResult {
    private Long orderSequence;
    private Long id;
    private String innerCategoryName;
    private String additionalDescription;
    private List<GetAllInnerProductsResultElement> innerProducts;
}
