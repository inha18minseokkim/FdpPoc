package com.example.fdppoc.domain.mapper;

import com.example.fdppoc.domain.entity.CustomerInterestProduct;
import com.example.fdppoc.domain.dto.GetMemberInterestProductsResult;
import com.example.fdppoc.domain.dto.SetProductInterestCriteria;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CustomerInterestProductServiceMapper {
    @Mappings({
            @Mapping(target = "innerProductId", expression = "java(element.getInnerProduct().getId())")
    }
    )
    GetMemberInterestProductsResult from(CustomerInterestProduct element);
}
