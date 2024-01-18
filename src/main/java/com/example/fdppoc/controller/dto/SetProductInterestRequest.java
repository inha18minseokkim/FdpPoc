package com.example.fdppoc.controller.dto;

import com.example.fdppoc.entity.BaseProduct;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

import java.io.Serializable;

@Builder
@Data
@ToString
public class SetProductInterestRequest implements Serializable {
    private String customerId;
    private Boolean isAvailable;
}
