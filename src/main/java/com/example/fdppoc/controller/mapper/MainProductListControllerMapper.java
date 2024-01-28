package com.example.fdppoc.controller.mapper;

import com.example.fdppoc.controller.dto.*;
import com.example.fdppoc.service.dto.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MainProductListControllerMapper {

    SearchInnerProductsResponseElement from(GetInnerProductListOut element);

    SearchAllInnerProductsResponseElement from2(GetAllInnerProductsOut element);

    GetAllProductCriteria from(LegacyAllInnerProductsRequest request);

    LegacyAllInnerProductsResponseElement from(GetAllProductResult element);
}
