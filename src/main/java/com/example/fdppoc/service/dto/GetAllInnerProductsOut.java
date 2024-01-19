package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@Data
@ToString
public class GetAllInnerProductsOut {
    private Long orderSequence;
    private Long id;
    private String innerCategoryName;
    private String additionalDescription;
    private List<GetAllInnerProductsOutElement> innerProducts;
}
