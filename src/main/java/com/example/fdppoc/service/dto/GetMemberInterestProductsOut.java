package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.InnerProduct;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class GetMemberInterestProductsOut {
    private InnerProduct innerProduct;
}
