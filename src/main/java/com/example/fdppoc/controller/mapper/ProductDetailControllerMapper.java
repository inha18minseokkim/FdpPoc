package com.example.fdppoc.controller.mapper;

import com.example.fdppoc.controller.dto.GetProductDetailResponse;
import com.example.fdppoc.domain.dto.GetProductPriceResult;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductDetailControllerMapper {
    GetProductDetailResponse from(GetProductPriceResult productPrice);

}
