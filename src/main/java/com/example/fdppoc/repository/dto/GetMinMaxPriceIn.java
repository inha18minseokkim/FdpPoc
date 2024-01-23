package com.example.fdppoc.repository.dto;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.entity.UserGroupCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetMinMaxPriceIn {
    private UserGroupCode regionGroup;
    private InnerProduct targetProduct;
    private String startDate;
    private String endDate;
}
