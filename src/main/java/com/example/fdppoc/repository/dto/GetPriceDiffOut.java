package com.example.fdppoc.repository.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetPriceDiffOut {
    private Long basePrice;
    private String baseDate;
    private Long pastPrice;
    private String pastDate;
}
