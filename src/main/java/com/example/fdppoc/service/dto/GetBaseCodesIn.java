package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetBaseCodesIn {
    private String categoryCode;
    private String itemCode;
}
