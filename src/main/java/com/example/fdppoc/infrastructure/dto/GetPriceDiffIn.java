package com.example.fdppoc.infrastructure.dto;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.UserGroupCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetPriceDiffIn {
    private UserGroupCode regionGroup;
    private InnerProduct targetProduct;
    private String startDate;
    private String endDate;
}
