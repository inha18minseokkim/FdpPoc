package com.example.fdppoc.service.mapper;

import com.example.fdppoc.entity.InnerCategory;
import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.service.dto.GetAllInnerCategoryResult;
import com.example.fdppoc.service.dto.GetAllInnerProductsResultElement;
import com.example.fdppoc.service.dto.SetInnerCategoryCriteria;
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
    GetAllInnerProductsResultElement from(InnerProduct element);

    GetAllInnerCategoryResult from(InnerCategory element);

    @Mapping(target="subProducts",ignore = true)
    InnerCategory from(SetInnerCategoryCriteria element);
}
