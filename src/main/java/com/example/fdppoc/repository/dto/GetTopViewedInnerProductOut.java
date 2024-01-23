package com.example.fdppoc.repository.dto;

import com.example.fdppoc.entity.InnerProduct;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GetTopViewedInnerProductOut {
    private InnerProduct innerProduct;
    private Long count;
}
