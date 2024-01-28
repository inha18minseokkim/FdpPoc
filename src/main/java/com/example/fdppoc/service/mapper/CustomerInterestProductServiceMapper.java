package com.example.fdppoc.service.mapper;

import com.example.fdppoc.entity.CustomerInterestProduct;
import com.example.fdppoc.service.dto.GetMemberInterestProductsOut;
import com.example.fdppoc.service.dto.SetProductInterestIn;
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
    CustomerInterestProduct from(SetProductInterestIn in);

    @Mappings({
            @Mapping(target = "innerProduct", expression = "java(element.getInnerProduct())")
    }
    )
    GetMemberInterestProductsOut from(CustomerInterestProduct element);
}
