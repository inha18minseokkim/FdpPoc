package com.example.fdppoc.repository.dto;

import com.example.fdppoc.entity.InnerProduct;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Optional;

@Data
@ToString
@Builder
public class GetPriceDiffListOut {
    private InnerProduct innerProduct;
    private Optional<String> baseDate;
    private Optional<Double> price;

}
