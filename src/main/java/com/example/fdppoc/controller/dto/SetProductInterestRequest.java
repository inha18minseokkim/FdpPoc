package com.example.fdppoc.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Data
@ToString
public class SetProductInterestRequest implements Serializable {
    private String customerId;
    private Boolean isAvailable;
}
