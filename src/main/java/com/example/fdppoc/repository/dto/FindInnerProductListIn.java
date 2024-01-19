package com.example.fdppoc.repository.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FindInnerProductListIn {
    private Boolean isAvailable;
    private Long innerCategoryId;
    private String searchKeyword;
}
