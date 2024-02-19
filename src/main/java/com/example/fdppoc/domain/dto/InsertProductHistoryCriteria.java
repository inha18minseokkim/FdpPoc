package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class InsertProductHistoryCriteria {
    private String innerProductId;
    private Long memberInfoId;
    private String regionGroupId;
}
