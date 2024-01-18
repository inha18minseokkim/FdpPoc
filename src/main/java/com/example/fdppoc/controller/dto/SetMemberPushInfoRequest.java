package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class SetMemberPushInfoRequest {
    private String customerId;
    private String businessCode;
    private Boolean isAgree;
}
