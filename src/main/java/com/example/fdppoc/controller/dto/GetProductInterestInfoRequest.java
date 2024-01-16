package com.example.fdppoc.controller.dto;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.MemberInfo;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetProductInterestInfoRequest {
    private String customerId;
}