package com.example.fdppoc.infrastructure.dto;

import com.example.fdppoc.domain.entity.InnerProduct;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Optional;

@Data
@ToString
@Builder
public class GetPriceDiffListOutDto {
    private String innerProductId;
    private Optional<String> baseDate;
    private Optional<Double> price;

}
