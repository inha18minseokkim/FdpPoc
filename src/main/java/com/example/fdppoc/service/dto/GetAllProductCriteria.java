package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetAllProductCriteria {
    private String customerId;
    private Long regionGroupId;
    private String baseDate;
}
