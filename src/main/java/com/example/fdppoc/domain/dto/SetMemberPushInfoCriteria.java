package com.example.fdppoc.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SetMemberPushInfoCriteria {
    private String customerId;
    private String businessCode;
    private Boolean isAgree;
}
