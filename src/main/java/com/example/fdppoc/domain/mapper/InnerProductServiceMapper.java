package com.example.fdppoc.domain.mapper;

import com.example.fdppoc.domain.dto.*;
import com.example.fdppoc.domain.entity.InnerProduct;
import com.example.fdppoc.infrastructure.repository.dto.FindInnerProductListIn;
import com.example.fdppoc.infrastructure.repository.dto.FindInnerProductWithFilterOut;
import com.example.fdppoc.infrastructure.repository.dto.FindInnerProductsWithFilterIn;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface InnerProductServiceMapper {
    FindInnerProductsWithFilterIn from(GetInnerProductsWithFilterCriteria in);

    @Mapping(target="baseProductIds",expression = "java(element.getBaseProducts().stream().map(ele->ele.getId()).collect(java.util.stream.Collectors.toList()))")
    GetInnerProductsWithFilterResult from(FindInnerProductWithFilterOut element);

    @Mappings({
            @Mapping(target = "baseProducts", ignore = true),
            @Mapping(target = "innerCategory",ignore = true)
    }
    )
    InnerProduct toEntity(SetInnerProductsCriteria element);

    FindInnerProductListIn from(GetInnerProductsCriteria in);
    @Mappings({
        //@Mapping(target="baseProductId",expression = "java(element.getBaseProduct().getId())"),
        @Mapping(target="baseProductIds", expression = "java(element.getBaseProducts().stream().map(elem -> elem.getId()).collect(java.util.stream.Collectors.toList()))"),
        @Mapping(target="innerCategoryId",expression = "java(element.getInnerCategory().getId())")
    })
    GetInnerProductsResult from(InnerProduct element);
}
