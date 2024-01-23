package com.example.fdppoc.repository.dto;

import com.example.fdppoc.entity.UserGroupCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetMinMaxPriceOut {
    private Long maxPrice;
    private Long minPrice;
}
