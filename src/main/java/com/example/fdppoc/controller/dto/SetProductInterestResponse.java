package com.example.fdppoc.controller.dto;

import com.example.fdppoc.code.ControllerResponse;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class SetProductInterestResponse {
    private Boolean isAvailable;
    private String innerProductId;
}
