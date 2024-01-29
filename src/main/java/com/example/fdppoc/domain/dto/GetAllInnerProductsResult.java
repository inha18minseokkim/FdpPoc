package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@Data
@ToString
public class GetAllInnerProductsResult {
    private Long orderSequence;
    private Long id;
    private String innerCategoryName;
    private String additionalDescription;
    private List<GetAllInnerProductsResultElement> innerProducts;
}
