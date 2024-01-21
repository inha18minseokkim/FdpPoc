package com.example.fdppoc.service.mapper;

import com.example.fdppoc.entity.InnerCategory;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.service.dto.GetAllInnerCategoryOut;
import com.example.fdppoc.service.dto.GetAllInnerProductsOutElement;
import com.example.fdppoc.service.dto.SetInnerCategoryIn;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface InnerCategoryServiceMapper {
    GetAllInnerProductsOutElement from(InnerProduct element);

    GetAllInnerCategoryOut from(InnerCategory element);

    @Mapping(target="subProducts",ignore = true)
    InnerCategory from(SetInnerCategoryIn element);
}
