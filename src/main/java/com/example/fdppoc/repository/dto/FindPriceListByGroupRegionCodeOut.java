package com.example.fdppoc.repository.dto;

import com.example.fdppoc.code.BaseRange;
import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.entity.UserCode;
import com.example.fdppoc.entity.UserGroupCode;
import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindPriceListByGroupRegionCodeOut {
    private String baseDate;
    private UserGroupCode regionGroupInfo;
    private InnerProduct innerProduct;
    private BaseRange baseRange;
    private Long price;
}
