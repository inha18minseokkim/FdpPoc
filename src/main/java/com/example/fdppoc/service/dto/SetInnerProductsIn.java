package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.entity.InnerCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class SetInnerProductsIn {
    private Long id;
    private Long baseProductId;
    private Boolean isMainMaterial;
    private Boolean isAvailable;
    private Long innerCategoryId;
    private Long orderSequence;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private String seasonStartDate;
    private String seasonEndDate;
    private String rowStatus;
}
