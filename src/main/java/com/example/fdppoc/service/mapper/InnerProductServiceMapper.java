package com.example.fdppoc.service.mapper;

import com.example.fdppoc.entity.InnerProduct;
import com.example.fdppoc.repository.dto.FindInnerProductListIn;
import com.example.fdppoc.repository.dto.FindInnerProductWithFilterOut;
import com.example.fdppoc.repository.dto.FindInnerProductsWithFilterIn;
import com.example.fdppoc.service.dto.*;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface InnerProductServiceMapper {
    FindInnerProductsWithFilterIn from(GetInnerProductsWithFilterIn in);

    @Mapping(target="baseProductId",expression = "java(element.getBaseProduct().getId())")
    GetInnerProductsWithFilterOut from(FindInnerProductWithFilterOut element);

    @Mappings({
            @Mapping(target = "baseProduct", ignore = true),
            @Mapping(target = "innerCategory",ignore = true)
    }
    )
    InnerProduct toEntity(SetInnerProductsIn element);

    FindInnerProductListIn from(GetInnerProductListIn in);
    @Mappings({
        @Mapping(target="baseProductId",expression = "java(element.getBaseProduct().getId())"),
        @Mapping(target="innerCategoryId",expression = "java(element.getInnerCategory().getId())")
    })
    GetInnerProductListOut from(InnerProduct element);
}
