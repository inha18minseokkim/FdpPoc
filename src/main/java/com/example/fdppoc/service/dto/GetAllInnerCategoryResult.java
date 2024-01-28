package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GetAllInnerCategoryResult {
    private Long id;
    private String innerCategoryName;
    private Long orderSequence;
    private String additionalDescription;
    private Boolean isAvailable;
}
