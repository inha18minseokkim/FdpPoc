package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetLatestBaseDateResult {
    private String baseDate;
}
