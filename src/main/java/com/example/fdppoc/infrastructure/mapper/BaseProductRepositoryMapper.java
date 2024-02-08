package com.example.fdppoc.infrastructure.mapper;

import com.example.fdppoc.domain.entity.BaseProduct;
import com.example.fdppoc.infrastructure.dto.FindBaseProductWithFilterOutDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BaseProductRepositoryMapper {
    @Mapping(target = "innerProductId",expression = "java(baseProduct.getInnerProduct() == null ? null : baseProduct.getInnerProduct().getId())")
    FindBaseProductWithFilterOutDto from(BaseProduct baseProduct);
}
