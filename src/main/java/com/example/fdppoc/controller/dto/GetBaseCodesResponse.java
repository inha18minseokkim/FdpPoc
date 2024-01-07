package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetBaseCodesResponse {
    private String responseCode;
    private List<GetBaseCodesResponseElement> list;
}
