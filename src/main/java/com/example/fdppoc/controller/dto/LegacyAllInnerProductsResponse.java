package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Data
public class LegacyAllInnerProductsResponse {
    private Long processCount;
    private List<LegacyAllInnerProductsResponseElement> list;
}
