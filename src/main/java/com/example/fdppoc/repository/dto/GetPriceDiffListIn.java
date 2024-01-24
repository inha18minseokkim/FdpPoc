package com.example.fdppoc.repository.dto;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.entity.UserGroupCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetPriceDiffListIn {
    private UserGroupCode regionGroup;
    private String startDate;
    private String endDate;
}
