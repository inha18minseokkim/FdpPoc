package com.example.fdppoc.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FindInnerProductListInDto {
    private Boolean isAvailable;
    private Long innerCategoryId;
    private String searchKeyword;
}
