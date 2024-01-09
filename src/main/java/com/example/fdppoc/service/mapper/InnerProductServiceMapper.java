package com.example.fdppoc.service.mapper;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.repository.dto.FindInnerProductWithFilterOut;
import com.example.fdppoc.repository.dto.FindInnerProductsWithFilterIn;
import com.example.fdppoc.service.dto.GetInnerProductsWithFilterIn;
import com.example.fdppoc.service.dto.GetInnerProductsWithFilterOut;
import com.example.fdppoc.service.dto.SetInnerProductsIn;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface InnerProductServiceMapper {
    FindInnerProductsWithFilterIn from(GetInnerProductsWithFilterIn in);

    GetInnerProductsWithFilterOut from(FindInnerProductWithFilterOut element);

    @Mapping(target="baseProduct",ignore = true)
    InnerProduct toEntity(SetInnerProductsIn element);
}
