package com.example.fdppoc.service.mapper;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.service.dto.GetAllInnerProductsOutElement;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface InnerCategoryServiceMapper {
    GetAllInnerProductsOutElement from(InnerProduct element);
}
