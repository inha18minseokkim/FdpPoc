package com.example.fdppoc.infrastructure.mapper;

import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.infrastructure.dto.FindInnerProductWithFilterOutDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface InnerProductRepositoryMapper {

    @Mappings({
            @Mapping(target="innerCategoryId",expression = "java(element.getInnerCategory().getId())"),
            @Mapping(target="baseProductIds",expression = "java(element.getBaseProducts().stream().map(e->e.getId()).toList())")
    })
    FindInnerProductWithFilterOutDto from(InnerProduct element);
}
