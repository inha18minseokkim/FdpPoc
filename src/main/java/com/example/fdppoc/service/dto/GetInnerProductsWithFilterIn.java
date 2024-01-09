package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GetInnerProductsWithFilterIn {
    private String classificationCode;
    private String categoryCode;
}
