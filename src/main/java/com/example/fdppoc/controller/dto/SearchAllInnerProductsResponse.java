package com.example.fdppoc.controller.dto;

import com.example.fdppoc.entity.InnerCategory;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class SearchAllInnerProductsResponse {
    private String baseDate;
    private List<SearchAllInnerProductsResponseElement> innerCategories;
}
