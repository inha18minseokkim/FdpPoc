package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class SearchAllInnerProductsResponseElement {
    private Long orderSequence;
    private Long id;
    private String innerCategoryName;
    private String additionalDescription;
    private List<SearchAllInnerProductsResponseSubElement> subList;
}
