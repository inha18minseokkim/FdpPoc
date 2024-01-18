package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SetMemberPushInfoIn {
    private String customerId;
    private String businessCode;
    private Boolean isAgree;
}
