package com.example.fdppoc.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class GetMemberPushInfoCriteria {
    private String customerId;
    private String businessCode;
}
