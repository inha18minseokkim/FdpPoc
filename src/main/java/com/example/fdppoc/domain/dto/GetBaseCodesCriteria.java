package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetBaseCodesCriteria {
    private String categoryCode;
    private String itemCode;
}
