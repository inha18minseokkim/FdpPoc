package com.example.fdppoc.domain.mapper;

import com.example.fdppoc.domain.dto.GetProductPriceCriteria;
import com.example.fdppoc.infrastructure.dto.FindPriceListByGroupRegionCodeIn;
import com.example.fdppoc.infrastructure.dto.GetTopViewedInnerProductIn;
import com.example.fdppoc.domain.dto.GetPopularProductCriteria;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductListServiceMapper {
    GetTopViewedInnerProductIn from(GetPopularProductCriteria criteria);

    FindPriceListByGroupRegionCodeIn from(GetProductPriceCriteria in);
}
