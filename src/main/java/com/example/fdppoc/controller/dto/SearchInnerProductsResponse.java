package com.example.fdppoc.controller.dto;

import com.example.fdppoc.code.ControllerResponse;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@Data
@ToString
public class SearchInnerProductsResponse {
    private ControllerResponse responseCode;
    private List<List<SearchInnerProductsResponseElement>> lists;
}
