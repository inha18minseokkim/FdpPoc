package com.example.fdppoc.repository.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class FindBaseProductWithFilterIn {
    private String categoryCode;
    private String itemCode;
}
