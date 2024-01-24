package com.example.fdppoc.repository.dto;

import com.example.fdppoc.entity.InnerProduct;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class GetPriceDiffListOut {
    private InnerProduct innerProduct;
    private String baseDate;
    private Double price;

}
