package com.example.fdppoc.controller.dto;

import com.example.fdppoc.code.ControllerResponse;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class SetBaseCodesResponse {
    private ControllerResponse responseCode;
}