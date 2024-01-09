package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Builder
@Getter
public class GetInnerProductsResponse {
    private String responseCode;
    List<GetInnerProductsResponseElement> lists;
}
