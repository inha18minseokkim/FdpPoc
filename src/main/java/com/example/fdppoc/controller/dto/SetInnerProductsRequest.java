package com.example.fdppoc.controller.dto;

import com.example.fdppoc.entity.BaseProduct;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Data
public class SetInnerProductsRequest {
    Integer requestSize;
    List<SetInnerProductsRequestElement> lists;
}
