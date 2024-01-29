package com.example.fdppoc.infrastructure.repository.dto;

import com.example.fdppoc.domain.entity.UserGroupCode;
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
