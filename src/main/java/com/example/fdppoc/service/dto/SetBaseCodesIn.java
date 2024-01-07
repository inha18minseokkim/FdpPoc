package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class SetBaseCodesIn {
    private Long id;
    private String categoryCode;    //pd_ctgr_cd
    private String itemCode;            //pd_lsar_cd
    private String kindCode;            //pd_knd_cd
    private String classCode;           //whls_rtl_dcd
    private String rankCode;            //pd_grade_cd
    private String unitName;            //snog_unit_nm
    private Float unitValue;            //snog_unit_val
    private Boolean isAvailable;            //사용여부
    private String categoryName;
    private String itemName;
    private String kindName;
    private String gradeName;
    private String rowStatus;
}
