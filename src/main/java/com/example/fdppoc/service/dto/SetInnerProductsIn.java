package com.example.fdppoc.service.dto;

import com.example.fdppoc.entity.BaseProduct;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SetInnerProductsIn {
    private Long id;
    private String categoryCode;    //pd_ctgr_cd
    private String itemCode;            //pd_lsar_cd
    private String kindCode;            //pd_knd_cd
    private String classCode;           //whls_rtl_dcd
    private String gradeCode;            //pd_grade_cd
    private Boolean isMainMaterial;
    private String classificationCode;
    private Long orderSequence;
    private String productName;
    private String additionalDescription;
    private Boolean isSeasonal;
    private String seasonStartDate;
    private String seasonEndDate;
    private String rowStatus;
}
