package com.example.fdppoc.controller.mapper;

import com.example.fdppoc.controller.dto.SearchAllInnerProductsResponseElement;
import com.example.fdppoc.controller.dto.SearchInnerProductsRequest;
import com.example.fdppoc.controller.dto.SearchInnerProductsResponseElement;
import com.example.fdppoc.service.dto.GetAllInnerProductsOut;
import com.example.fdppoc.service.dto.GetInnerProductListIn;
import com.example.fdppoc.service.dto.GetInnerProductListOut;
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
}
