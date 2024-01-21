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
    GetBaseCodesIn from(GetBaseCodesRequest request);
    GetBaseCodesResponseElement from(GetBaseCodesOut element);
    SetBaseCodesIn from(SetBaseCodesRequestElement element);
    GetInnerProductsWithFilterIn from(GetInnerProductsRequest request);

    GetInnerProductsResponseElement from(GetInnerProductsWithFilterOut element);

    SetInnerProductsIn from(SetInnerProductsRequestElement element);

    GetAllInnerCategoryIn from(GetInnerCategoryRequest request);

    GetInnerCategoryResponseElement from(GetAllInnerCategoryOut element);
}
