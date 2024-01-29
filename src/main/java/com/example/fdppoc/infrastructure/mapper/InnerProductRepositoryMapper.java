package com.example.fdppoc.infrastructure.mapper;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.infrastructure.dto.FindInnerProductWithFilterOut;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface InnerProductRepositoryMapper {

    @Mappings({
            @Mapping(target="innerCategoryId",expression = "java(element.getInnerCategory().getId())")
    })
    FindInnerProductWithFilterOut from(InnerProduct element);
}
