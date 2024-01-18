package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GetmemberPushInfoRequest {
    private String customerId;
    private String businessCode;
    private Boolean isAgree;
}
