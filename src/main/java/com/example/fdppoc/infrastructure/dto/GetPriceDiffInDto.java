package com.example.fdppoc.infrastructure.dto;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.domain.entity.UserGroupCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetPriceDiffInDto {
    private String regionGroupCodeId;
    private String targetInnerProductId;
    private String startDate;
    private String endDate;
}
