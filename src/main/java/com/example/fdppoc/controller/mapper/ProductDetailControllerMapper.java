package com.example.fdppoc.controller.mapper;

import com.example.fdppoc.controller.dto.GetProductDetailIn;
import com.example.fdppoc.controller.dto.GetProductDetailOut;
import com.example.fdppoc.service.dto.GetProductPriceIn;
import com.example.fdppoc.service.dto.GetProductPriceOut;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductDetailControllerMapper {
    GetProductDetailOut from(GetProductPriceOut productPrice);
}
