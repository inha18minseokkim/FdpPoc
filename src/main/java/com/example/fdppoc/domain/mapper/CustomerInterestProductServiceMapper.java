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
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "innerProduct", source = "targetProduct")
    }
    )
    CustomerInterestProduct from(SetProductInterestCriteria in);

    @Mappings({
            @Mapping(target = "innerProduct", expression = "java(element.getInnerProduct())")
    }
    )
    GetMemberInterestProductsResult from(CustomerInterestProduct element);
}
