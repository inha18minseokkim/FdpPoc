package com.example.fdppoc.controller.mapper;

import com.example.fdppoc.controller.dto.GetProductDetailResponse;
import com.example.fdppoc.controller.dto.GetProductInterestInfoRequest;
import com.example.fdppoc.service.dto.GetProductInterestIn;
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
    GetProductDetailResponse from(GetProductPriceOut productPrice);

}
