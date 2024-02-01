package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetAllProductCriteria {
    private String customerId;
    private String regionGroupId;
    private String baseDate;
}
