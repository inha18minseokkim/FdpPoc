package com.example.fdppoc.domain.mapper;

import com.example.fdppoc.infrastructure.repository.dto.FindPriceListByGroupRegionCodeIn;
import com.example.fdppoc.domain.dto.GetProductPriceCriteria;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductDetailServiceMapper {
    FindPriceListByGroupRegionCodeIn from(GetProductPriceCriteria in);
}
