package com.example.fdppoc.domain.dto;

import lombok.*;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetDetilPriceListCriteria {
    private String innerProductId;
    private String regionGroupId;
    private String baseDate;

}
