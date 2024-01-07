package com.example.fdppoc.repository.mapper;

import com.example.fdppoc.entity.BaseProduct;
import com.example.fdppoc.repository.dto.FindBaseProductWithFilterOut;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BaseProductRepositoryMapper {
    FindBaseProductWithFilterOut from(BaseProduct baseProduct);
}
