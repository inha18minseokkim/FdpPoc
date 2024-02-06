package com.example.fdppoc.domain.mapper;

import com.example.fdppoc.domain.dto.GetDetailPriceLegacyResultSubElement;
import com.example.fdppoc.domain.dto.GetProductPriceCriteria;
import com.example.fdppoc.infrastructure.dto.FindPriceListByGroupRegionCodeInDto;
import com.example.fdppoc.infrastructure.dto.FindPriceListByGroupRegionCodeOut;
import com.example.fdppoc.infrastructure.dto.GetTopViewedInnerProductInDto;
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
    GetTopViewedInnerProductInDto from(GetPopularProductCriteria criteria);

    FindPriceListByGroupRegionCodeInDto from(GetProductPriceCriteria in);

    GetDetailPriceLegacyResultSubElement toSubElement(FindPriceListByGroupRegionCodeOut element);
}
