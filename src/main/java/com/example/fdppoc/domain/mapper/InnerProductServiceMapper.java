package com.example.fdppoc.domain.mapper;

import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.infrastructure.dto.FindInnerProductListInDto;
import com.example.fdppoc.infrastructure.dto.FindInnerProductListOutDto;
import com.example.fdppoc.infrastructure.dto.FindInnerProductWithFilterOutDto;
import com.example.fdppoc.infrastructure.dto.FindInnerProductsWithFilterInDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface InnerProductServiceMapper {
    FindInnerProductsWithFilterInDto from(GetInnerProductsWithFilterCriteria in);

    //@Mapping(target="baseProductIds",expression = "java(element.getBaseProductIds().stream().mapToLong(ele->ele.getId()).toList())")
    GetInnerProductsWithFilterResult from(FindInnerProductWithFilterOutDto element);

    @Mappings({
            @Mapping(target = "baseProducts", ignore = true),
            @Mapping(target = "innerCategory",ignore = true)
    }
    )
    InnerProduct toEntity(SetInnerProductsCriteria element);

    FindInnerProductListInDto from(GetInnerProductsCriteria in);

    GetInnerProductsResult from(InnerProduct element);
}
