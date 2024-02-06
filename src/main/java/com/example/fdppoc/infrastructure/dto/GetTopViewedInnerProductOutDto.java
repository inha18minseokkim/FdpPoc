package com.example.fdppoc.infrastructure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetTopViewedInnerProductOutDto {
    private String innerProductId;
    private Long count;
}
