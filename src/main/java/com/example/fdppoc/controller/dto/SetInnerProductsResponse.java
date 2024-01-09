package com.example.fdppoc.controller.dto;

import com.example.fdppoc.code.ControllerResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class SetInnerProductsResponse {
    private ControllerResponse responseCode;
}
