package com.example.fdppoc.domain.dto;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetDetailPriceLegacyResultSubElement {
    private String baseDate;
    private Double price;
}
