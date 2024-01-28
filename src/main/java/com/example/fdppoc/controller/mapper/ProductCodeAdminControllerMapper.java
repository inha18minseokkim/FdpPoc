package com.example.fdppoc.controller.mapper;

import com.example.fdppoc.controller.dto.*;
import com.example.fdppoc.service.dto.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductCodeAdminControllerMapper {
    GetBaseCodesCriteria from(GetBaseCodesRequest request);
    GetBaseCodesResponseElement from(GetBaseCodesResult element);
    SetBaseCodesCriteria from(SetBaseCodesRequestElement element);
    GetInnerProductsWithFilterCriteria from(GetInnerProductsRequest request);

    GetInnerProductsResponseElement from(GetInnerProductsWithFilterResult element);

    SetInnerProductsCriteria from(SetInnerProductsRequestElement element);

    GetAllInnerCategoryCriteria from(GetInnerCategoryRequest request);

    GetInnerCategoryResponseElement from(GetAllInnerCategoryResult element);
}
