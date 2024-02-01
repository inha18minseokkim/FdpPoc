package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetProductInterestInfoResponse {
    private String innerProductId;
    private Boolean isAvailable;
}
