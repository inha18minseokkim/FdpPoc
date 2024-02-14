package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@Data
@ToString
public class SearchInnerProductsResponse {
    private List<SearchInnerProductsResponseElement> lists;
}
