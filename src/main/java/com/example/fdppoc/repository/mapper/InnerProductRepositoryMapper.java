package com.example.fdppoc.repository.mapper;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.repository.dto.FindInnerProductWithFilterOut;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface InnerProductRepositoryMapper {
    FindInnerProductWithFilterOut from(InnerProduct element);
}
