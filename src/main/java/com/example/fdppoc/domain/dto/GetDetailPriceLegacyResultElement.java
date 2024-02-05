package com.example.fdppoc.domain.dto;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.controller.dto.GetProductDetailLegacyResponseSubElement;
import lombok.*;

import java.util.List;
@Builder
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetDetailPriceLegacyResultElement {
    private BaseRange baseRange;
    private Double basePrice;
    private Double minimumPrice;
    private Double maximumPrice;
    private Long listSize;
    private List<GetDetailPriceLegacyResultSubElement> list;
}
