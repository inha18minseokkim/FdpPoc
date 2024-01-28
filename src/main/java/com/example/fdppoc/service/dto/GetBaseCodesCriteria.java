package com.example.fdppoc.service.dto;

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
