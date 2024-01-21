package com.example.fdppoc.repository.mapper;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.repository.dto.FindInnerProductWithFilterOut;
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
